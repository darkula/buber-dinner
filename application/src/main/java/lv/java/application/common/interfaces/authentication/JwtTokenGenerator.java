package lv.java.application.common.interfaces.authentication;

import lv.java.domain.user.User;

public interface JwtTokenGenerator {
    String generateToken(User user);

    String getUserName(String token);

    boolean isTokenValid(String token);
}
