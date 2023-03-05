package lv.java.domain.user;

import lv.java.domain.common.models.AggregateRoot;
import lv.java.domain.user.value_objects.UserId;

import java.time.LocalDateTime;

public final class User extends AggregateRoot<UserId> {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

    public User(String firstName, String lastName, String email, String password) {
        super(UserId.createUnique());
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
