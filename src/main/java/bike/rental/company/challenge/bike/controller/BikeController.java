package bike.rental.company.challenge.bike.controller;

import bike.rental.company.challenge.bike.dao.BikeRepository;
import bike.rental.company.challenge.bike.model.Bike;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

//@RestController
//@RequestMapping(value = "/bike")
public class BikeController {
    private static final Logger logger = LoggerFactory.getLogger(BikeController.class);

    private BikeRepository bikeRepository;



    @Autowired
    public BikeController(BikeRepository bikeRepository){
        this.bikeRepository = bikeRepository;
    }

      // TODO: show filtered paged bikes to be selected in Front End
    //@GetMapping("/showFiltered")
    //public ResponseEntity showBikes(@RequestParam(defaultValue = "", required = false) String query,
    //                                @RequestParam(defaultValue = "20", required = false) int pageSize,
    //                                @RequestParam(defaultValue = "0", required = false) int page){}



}
