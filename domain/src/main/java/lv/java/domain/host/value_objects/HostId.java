package lv.java.domain.host.value_objects;

import lv.java.domain.common.models.ValueObject;

import java.util.UUID;

public final class HostId extends ValueObject {
    private final UUID value;

    private HostId(UUID value) {
        this.value = value;
    }

    public static HostId createUnique() {
        return new HostId(UUID.randomUUID());
    }

    public UUID getValue() {
        return value;
    }
}
