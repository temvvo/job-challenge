package bike.rental.company.challenge.rental.controller;

import bike.rental.company.challenge.bike.dao.BikeRepository;
import bike.rental.company.challenge.bike.model.Bike;
import bike.rental.company.challenge.client.dao.ClientRepository;
import bike.rental.company.challenge.client.model.Client;
import bike.rental.company.challenge.rental.dao.RentalItemRepository;
import bike.rental.company.challenge.rental.model.RentalItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;



public class RentalItemController {
    private static final Logger logger = LoggerFactory.getLogger(RentalItemController.class);

    private RentalItemRepository rentalItemRepository;
    private BikeRepository bikeRepository;
    private ClientRepository clientRepository;

    @Autowired
    public RentalItemController(
            RentalItemRepository rentalItemRepository, BikeRepository bikeRepository,
            ClientRepository clientRepository){

        this.rentalItemRepository = rentalItemRepository;
        this.clientRepository = clientRepository;
        this.bikeRepository = bikeRepository;

    }


    public boolean rentBike(long bikeId, long clientId,Date startDate,Date expectedEndDate){
       Optional<Bike> bikeResult = bikeRepository.findById(bikeId);
       if(!bikeResult.isPresent()) {
           logger.warn("Rent could not be done. Could not find bike with id: "+ bikeId);
           return false;
       }

       Optional<Client> clientResult = clientRepository.findById(clientId);
       if(!clientResult.isPresent()) {
           logger.warn("Rent could not be done. Could not find client with id: "+ clientId);
           return false;
       }

       Bike bike = bikeResult.get();
       Client client = clientResult.get();

       RentalItem rentalItem = new RentalItem();
       rentalItem.setClient(client);
       rentalItem.setBike(bike);
       rentalItem.setExpectedEndDate(expectedEndDate);
        // Time in UTC+0
        Instant instant = Instant.now();
        rentalItem.setStartDate(Date.from(instant));
        rentalItemRepository.save(rentalItem);

       logger.debug("Rent successfully done");

        return true;
    }

    public boolean rentFirstBikeAvailable(long clientId,Date startDate,Date expectedEndDate ){
        Optional<Bike> bikeResult = bikeRepository.findFirstByAvailableTrue();
        if(!bikeResult.isPresent()) {
            logger.warn("Rent could not be done. No bikes available.");
            return false;
        }

        Optional<Client> clientResult = clientRepository.findById(clientId);
        if(!clientResult.isPresent()) {
            logger.warn("Rent could not be done. Could not find client with id: "+ clientId);
            return false;
        }

        Bike bike = bikeResult.get();
        Client client = clientResult.get();

        RentalItem rentalItem = new RentalItem();
        rentalItem.setClient(client);
        rentalItem.setBike(bike);
        rentalItem.setExpectedEndDate(expectedEndDate);
        // Time in UTC+0
        Instant instant = Instant.now();
        rentalItem.setStartDate(Date.from(instant));
        rentalItemRepository.save(rentalItem);

        logger.debug("Rent successfully done.");

        return true;
    }
}
