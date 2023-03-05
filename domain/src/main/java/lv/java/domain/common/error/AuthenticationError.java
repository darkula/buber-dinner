package lv.java.domain.common.error;

public final class AuthenticationError {
    public static final Error INVALID_CREDENTIALS = Error.validation("Auth.InvalidCred", "Invalid credentials.");

    private AuthenticationError() throws IllegalAccessException {
        throw new IllegalAccessException();
    }
}
