package bike.rental.company.challenge.client.dao;

import bike.rental.company.challenge.client.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {

}
