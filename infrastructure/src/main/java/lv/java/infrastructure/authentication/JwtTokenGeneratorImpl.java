package lv.java.infrastructure.authentication;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.DefaultJwtSignatureValidator;
import lv.java.application.common.interfaces.authentication.JwtTokenGenerator;
import lv.java.domain.entities.User;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class JwtTokenGeneratorImpl implements JwtTokenGenerator {
    private final Clock clock;
    private final JwtSettings jwtSettings;
    private final Key signingKey;
    private final SignatureAlgorithm signatureAlgorithm;

    public JwtTokenGeneratorImpl(Clock clock, JwtSettings jwtSettings) {
        this.clock = Objects.requireNonNull(clock);
        this.jwtSettings = Objects.requireNonNull(jwtSettings);
        this.signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = this.jwtSettings.secret().getBytes(StandardCharsets.UTF_8);
        this.signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
    }

    @Override
    public String generateToken(User user) {
        JwtBuilder builder = Jwts.builder()
                .setSubject(user.getEmail())
                .claim("given_name", user.getFirstName())
                .claim("family_name", user.getLastName())
                .setId(UUID.randomUUID().toString())
                .setExpiration(Date.from(LocalDateTime.now(this.clock)
                        .plusMinutes(this.jwtSettings.expiryMinutes())
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .setIssuer(this.jwtSettings.issuer())
                .setAudience(this.jwtSettings.audience())
                .signWith(signatureAlgorithm, signingKey);

        return builder.compact();
    }

    @Override
    public String getUserName(String token) {
        return Jwts.parser()
                .setSigningKey(this.signingKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    @Override
    public boolean isTokenValid(String token) {
        String[] chunks = token.split("\\.");
        if (chunks.length != 3) return false;

        String tokenWithoutSignature = chunks[0] + "." + chunks[1];
        String signature = chunks[2];

        DefaultJwtSignatureValidator validator = new DefaultJwtSignatureValidator(signatureAlgorithm, signingKey);

        return validator.isValid(tokenWithoutSignature, signature);
    }
}
