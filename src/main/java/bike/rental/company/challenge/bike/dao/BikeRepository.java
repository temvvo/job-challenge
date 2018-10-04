package bike.rental.company.challenge.bike.dao;

import bike.rental.company.challenge.bike.model.Bike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BikeRepository extends JpaRepository <Bike, Long> {
    Optional<Bike> findFirstByAvailableTrue();
}
