package peaksoft.dto;

import lombok.Builder;
import peaksoft.model.enums.Role;

@Builder
public record AuthenticationResponse(
        String email,
        String token,
        Role role
) {
}