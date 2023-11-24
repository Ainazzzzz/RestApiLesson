package peaksoft.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "us_gen")
    @SequenceGenerator(name = "us_gen", sequenceName = "us_seq", allocationSize = 1,initialValue = 2)
    private long id;
    private String name;
    private String surname;
    private String password;
    private String username;
}
