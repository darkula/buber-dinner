package lv.java.domain.host.value_objects;

import lv.java.domain.common.models.ValueObject;

import java.util.UUID;

public final class HostId extends ValueObject {
    private final String value;

    private HostId(String value) {
        this.value = value;
    }

    public static HostId createUnique() {
        return new HostId(UUID.randomUUID().toString());
    }

    public static HostId create(String id) {
        return new HostId(id);
    }

    public String getValue() {
        return value;
    }
}
