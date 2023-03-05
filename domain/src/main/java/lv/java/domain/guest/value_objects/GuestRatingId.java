package lv.java.domain.guest.value_objects;

import lv.java.domain.common.models.ValueObject;

import java.util.UUID;

public final class GuestRatingId extends ValueObject {
    private final UUID value;

    private GuestRatingId(UUID value) {
        this.value = value;
    }

    public static GuestRatingId createUnique() {
        return new GuestRatingId(UUID.randomUUID());
    }

    public UUID getValue() {
        return value;
    }
}
