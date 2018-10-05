package bike.rental.company.challenge.rental.controller;

import bike.rental.company.challenge.client.dao.ClientRepository;
import bike.rental.company.challenge.client.model.Client;
import bike.rental.company.challenge.general.utils.RentalCalculateUtils;
import bike.rental.company.challenge.rental.dao.RentalRepository;
import bike.rental.company.challenge.rental.model.Rental;

import bike.rental.company.challenge.rental.model.RentalItem;
import bike.rental.company.challenge.rental.response.GenericApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/rental")
public class RentalController {

    private static final Logger logger = LoggerFactory.getLogger(RentalController.class);
    private RentalRepository rentalRepository;
    private ClientRepository clientRepository;

    @Autowired
    public RentalController(
            RentalRepository rentalRepository, ClientRepository clientRepository){

        this.rentalRepository = rentalRepository;
        this.clientRepository = clientRepository;
    }


    //TODO: Preauthorize using jwt token role and credentials
    @PostMapping("/purchase/{clientId}")
    public GenericApiResponse rent(@PathVariable long clientId,
                                                     @RequestBody List<RentalItem> items){
        logger.info("POST /rental/purchase/" + clientId + " called");

        if(items == null || items.isEmpty())
            return new GenericApiResponse(HttpStatus.BAD_REQUEST,"none bike was selected");

        Optional<Client> payerResult = clientRepository.findById(clientId);
        if (!payerResult.isPresent()) {
            return new GenericApiResponse(HttpStatus.BAD_REQUEST, "Payer does not exists in our database");
        }
        double totalAmount = 0;
        //TODO: Check bikes availability and clients existence.
        //For testing purpose, only existing mocked clients will be used
        for (RentalItem rentalItems : items ) {
            totalAmount += calculateItemAmount(rentalItems.getStartDate(),rentalItems.getExpectedEndDate());
        }
        totalAmount = RentalCalculateUtils.applyPromotions(totalAmount,items.size());

        Rental rental = new Rental();
        rental.setPayer(payerResult.get());
        rental.setTotalAmount(totalAmount);
        rental.setRentalItems(items);
        rentalRepository.save(rental);

        return new  GenericApiResponse(
                HttpStatus.OK,
                "Rental successfully done");
    }

    private double calculateItemAmount(Date startDate, Date expectedEndDate) {
        Duration duration = Duration.between(startDate.toInstant(), expectedEndDate.toInstant());
        return RentalCalculateUtils.getPricePerTimeDifference(duration);
    }
}

