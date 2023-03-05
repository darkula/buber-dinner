package lv.java.application.authentication.common;

import lv.java.domain.user.User;

public record AuthenticationResult(User user, String token) {
}
