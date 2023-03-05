package lv.java.contracts.authentication;

public record RegisterRequest(
        String firstName,
        String lastName,
        String email,
        String password) {
}
