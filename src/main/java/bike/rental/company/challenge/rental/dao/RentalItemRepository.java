package bike.rental.company.challenge.rental.dao;

import bike.rental.company.challenge.rental.model.RentalItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentalItemRepository extends JpaRepository<RentalItem, Long> {


}
