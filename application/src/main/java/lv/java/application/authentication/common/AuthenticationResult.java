package lv.java.application.authentication.common;

import lv.java.domain.entities.User;

public record AuthenticationResult(User user, String token) {
}
