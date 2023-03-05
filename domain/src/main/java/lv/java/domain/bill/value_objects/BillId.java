package lv.java.domain.bill.value_objects;

import lv.java.domain.common.models.ValueObject;

import java.util.UUID;

public final class BillId extends ValueObject {
    private final UUID value;

    private BillId(UUID value) {
        this.value = value;
    }

    public static BillId createUnique() {
        return new BillId(UUID.randomUUID());
    }

    public UUID getValue() {
        return value;
    }
}
