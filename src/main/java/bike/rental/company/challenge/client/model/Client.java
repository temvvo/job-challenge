package bike.rental.company.challenge.client.model;

import lombok.Data;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Client {
    @GeneratedValue
    @Id
    long id;
    String email;

    @Column(unique = true)
    String ssn;
    String name;
    String surname;
    String address;
    String zip;

    public Client(String email, String ssn, String name, String surname,String address,String zip) {
        setEmail(email);
        setSsn(ssn);
        setName(name);
        setSurname(surname);
        setAddress(address);
        setZip(zip);
    }
}
