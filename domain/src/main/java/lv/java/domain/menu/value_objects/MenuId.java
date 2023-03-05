package lv.java.domain.menu.value_objects;

import lv.java.domain.common.models.ValueObject;

import java.util.UUID;

public final class MenuId extends ValueObject {
    private final UUID value;

    private MenuId(UUID value) {
        this.value = value;
    }

    public static MenuId createUnique() {
        return new MenuId(UUID.randomUUID());
    }

    public UUID getValue() {
        return value;
    }
}
