package peaksoft.dto;

import lombok.Data;

@Data
public class CustomerRequest {
    private String name;
    private String surname;
    private String email;
    private int age;
}
