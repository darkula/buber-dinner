package lv.java.domain.dinner.value_objects;

import lv.java.domain.common.models.ValueObject;

import java.util.UUID;

public final class ReservationId extends ValueObject {
    private final UUID value;

    private ReservationId(UUID value) {
        this.value = value;
    }

    public static ReservationId createUnique() {
        return new ReservationId(UUID.randomUUID());
    }

    public UUID getValue() {
        return value;
    }
}
