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
    private String username;

public CustomerResponse(long id, String email, int age,  String username) {
        this.id = id;
        this.email = email;
        this.age = age;
        this.username = username;
    }

    public CustomerResponse( String password, String username) {

        this.password = password;
        this.username = username;
    }

//    public CustomerResponse(long id, String name, String surname, String email, int age, String password, String username) {
//        this.id = id;
//        this.name = name;
//        this.surname = surname;
//        this.email = email;
//        this.age = age;
//        this.password = password;
//        this.username = username;
//    }

    @Override
    public String toString() {
        return "CustomerResponse{" +
                "password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
