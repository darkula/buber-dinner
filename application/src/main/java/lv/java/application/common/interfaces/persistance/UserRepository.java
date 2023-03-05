package lv.java.application.common.interfaces.persistance;

import lv.java.domain.user.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> getUserByEmail(String email);

    void add(User user);
}
