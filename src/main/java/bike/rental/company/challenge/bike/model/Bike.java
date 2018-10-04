package bike.rental.company.challenge.bike.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Bike {
    @Id
    @GeneratedValue
    long id;
    int wheelSize;
    String color;
    String type;
    boolean available;
}
