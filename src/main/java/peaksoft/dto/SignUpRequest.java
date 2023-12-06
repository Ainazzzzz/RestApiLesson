package peaksoft.dto;

import lombok.Builder;
import peaksoft.model.enums.Role;

@Builder
public record SignUpRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        Role role
) {
}