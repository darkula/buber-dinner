package lv.java.domain.menu.value_objects;

import lv.java.domain.common.models.ValueObject;

import java.util.UUID;

public final class MenuId extends ValueObject {
    private final String value;

    private MenuId(String value) {
        this.value = value;
    }

    public static MenuId createUnique() {
        return new MenuId(UUID.randomUUID().toString());
    }

    public String getValue() {
        return value;
    }
}
