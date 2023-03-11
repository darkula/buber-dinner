package lv.java.domain.dinner.value_objects;

import lv.java.domain.common.models.ValueObject;

import java.util.UUID;

public final class DinnerId extends ValueObject {
    private final String value;

    private DinnerId(String value) {
        this.value = value;
    }

    public static DinnerId createUnique() {
        return new DinnerId(UUID.randomUUID().toString());
    }

    public String getValue() {
        return value;
    }
}
