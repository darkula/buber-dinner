package lv.java.domain.menu.entities;

import lv.java.domain.common.models.Entity;
import lv.java.domain.menu.value_objects.MenuSectionId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MenuSection extends Entity<MenuSectionId> {
    private final List<MenuItem> items = new ArrayList<>();
    private final String name;
    private final String description;

    private MenuSection(MenuSectionId menuSectionId, String name, String description) {
        super(menuSectionId);
        this.name = name;
        this.description = description;
    }

    public static MenuSection create(String name, String description) {
        return new MenuSection(MenuSectionId.createUnique(), name, description);
    }

    public List<MenuItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
