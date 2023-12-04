package peaksoft.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerResponse {
    private long id;
    private String name;
    private String surname;
    private String email;
    private int age;
    private String password;


public CustomerResponse(long id, String email, int age) {
        this.id = id;
        this.email = email;
        this.age = age;
           }




}
