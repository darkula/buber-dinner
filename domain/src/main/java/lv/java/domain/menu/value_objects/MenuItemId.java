package lv.java.domain.menu.value_objects;

import lombok.Getter;
import lv.java.domain.common.models.ValueObject;

import java.util.UUID;

@Getter
public final class MenuItemId extends ValueObject {
    private final UUID value;

    private MenuItemId(UUID value) {
        this.value = value;
    }

    public static MenuItemId createUnique() {
        return new MenuItemId(UUID.randomUUID());
    }

}
