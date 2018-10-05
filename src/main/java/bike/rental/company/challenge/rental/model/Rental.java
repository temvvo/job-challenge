package bike.rental.company.challenge.rental.model;

import bike.rental.company.challenge.client.model.Client;
import com.fasterxml.jackson.annotation.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
//@Table(name = "rental")
@Table
@EqualsAndHashCode(callSuper = false)
//@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ToString
public class Rental {

    @GeneratedValue
    @Id
    private long id;

    @Getter
    @Setter
    @OneToMany
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private List<RentalItem> rentalItems;

    @Getter
    @Setter
    @OneToOne
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Client payer;

    @Getter
    @Setter
    private double totalAmount;


    @JsonIgnore
    @CreatedDate
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    private Date created;

    @JsonIgnore
    @LastModifiedDate
    @Column(insertable = false)
    private Date lastModified;



}
