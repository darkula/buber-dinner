package lv.java.domain.common.error;

public final class UserError {
    public static final Error DUPLICATE_EMAIL = Error.conflict("User.DuplicateEmail", "Email is already in use.");

    private UserError() throws IllegalAccessException {
        throw new IllegalAccessException();
    }
}
