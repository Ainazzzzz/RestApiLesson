package peaksoft.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class CustomerRequest {
    @NotNull(message = "name must not be null")
    private String name;
    @NotNull(message = "surname must not be null")
    private String surname;
    @NotNull(message = "email must not be null")
    @Email(message = "email must be valid")
    private String email;
    @NotNull(message = "password must not be null")
    private int age;
}
