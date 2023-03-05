package lv.java.infrastructure.persistance;

import lv.java.application.common.interfaces.persistance.UserRepository;
import lv.java.domain.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private final List<User> users = new ArrayList<>();

    @Override
    public Optional<User> getUserByEmail(String email) {
        return this.users.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    @Override
    public void add(User user) {
        this.users.add(user);
    }
}
