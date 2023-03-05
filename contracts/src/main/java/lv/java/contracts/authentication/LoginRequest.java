package lv.java.contracts.authentication;

public record LoginRequest(
        String email,
        String password) {
}
