package bike.rental.company.challenge.bike.controller;

import bike.rental.company.challenge.bike.dao.BikeRepository;
import bike.rental.company.challenge.bike.model.Bike;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class BikeController {
    private static final Logger logger = LoggerFactory.getLogger(BikeController.class);

    private BikeRepository bikeRepository;

    @Autowired
    public BikeController(BikeRepository bikeRepository){
        this.bikeRepository = bikeRepository;
    }
    public Bike getBikeById(long id){
        Optional<Bike> result = bikeRepository.findById(id);
        return result.get();
    }


}
