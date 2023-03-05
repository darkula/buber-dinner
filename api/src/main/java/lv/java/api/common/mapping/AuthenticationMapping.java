package lv.java.api.common.mapping;

import lv.java.application.authentication.common.AuthenticationResult;
import lv.java.contracts.authentication.AuthenticationResponse;

public final class AuthenticationMapping {

    public static AuthenticationResponse mapToResponse(AuthenticationResult result) {

        return new AuthenticationResponse(
                result.user().getId().getValue(),
                result.user().getFirstName(),
                result.user().getLastName(),
                result.user().getEmail(),
                result.token());
    }

    private AuthenticationMapping() throws IllegalAccessException {
        throw new IllegalAccessException();
    }
}
