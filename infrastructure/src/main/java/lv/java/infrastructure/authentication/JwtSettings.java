package lv.java.infrastructure.authentication;

public record JwtSettings(
        String secret,
        int expiryMinutes,
        String issuer,
        String audience
) {
}
