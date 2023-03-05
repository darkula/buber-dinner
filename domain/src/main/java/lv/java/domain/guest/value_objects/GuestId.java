package lv.java.domain.guest.value_objects;

import lv.java.domain.common.models.ValueObject;

import java.util.UUID;

public final class GuestId extends ValueObject {
    private final UUID value;

    private GuestId(UUID value) {
        this.value = value;
    }

    public static GuestId createUnique() {
        return new GuestId(UUID.randomUUID());
    }

    public UUID getValue() {
        return value;
    }
}
