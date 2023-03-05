package lv.java.domain.user.value_objects;

import lv.java.domain.common.models.ValueObject;

import java.util.UUID;

public final class UserId extends ValueObject {
    private final UUID value;

    private UserId(UUID value) {
        this.value = value;
    }

    public static UserId createUnique() {
        return new UserId(UUID.randomUUID());
    }

    public UUID getValue() {
        return value;
    }
}
