package peaksoft.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cust_gen")
    @SequenceGenerator(name = "cust_gen", sequenceName = "cust_seq", allocationSize = 1,initialValue = 2)
    private long id;
    private String email;
    private int age;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;


    public Customer() {

    }

}
