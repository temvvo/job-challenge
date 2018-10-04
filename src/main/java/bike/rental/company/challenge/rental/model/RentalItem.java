package bike.rental.company.challenge.rental.model;


import bike.rental.company.challenge.bike.model.Bike;
import bike.rental.company.challenge.client.model.Client;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

import java.util.Date;

@Data
@Entity
@Table(name = "retal_item")
public class RentalItem {
    @GeneratedValue
    @Id
    private long id;


    private double unitPrice;

    @OneToOne
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Client client;

    @OneToOne
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Bike bike;

    private Date startDate;
    private Date endDate;
    private Date expectedEndDate;

    @CreatedDate
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    private Date created;

    @LastModifiedDate
    @Column(
            insertable = false
    )
    private Date lastModified;


}
