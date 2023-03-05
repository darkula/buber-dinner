package lv.java.contracts.authentication;

import java.util.UUID;

public record AuthenticationResponse(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String token) {
}
