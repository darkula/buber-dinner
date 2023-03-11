package lv.java.domain.menu.value_objects;

import lombok.Getter;
import lv.java.domain.common.models.ValueObject;

import java.util.UUID;

@Getter
public final class MenuSectionId extends ValueObject {
    private final String value;

    private MenuSectionId(String value) {
        this.value = value;
    }

    public static MenuSectionId createUnique() {
        return new MenuSectionId(UUID.randomUUID().toString());
    }

}
