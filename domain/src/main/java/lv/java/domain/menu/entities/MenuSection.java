package lv.java.domain.menu.entities;

import lv.java.domain.common.models.Entity;
import lv.java.domain.menu.value_objects.MenuSectionId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class MenuSection extends Entity<MenuSectionId> {
    private final List<MenuItem> items;
    private final String name;
    private final String description;

    private MenuSection(MenuSectionId menuSectionId, String name, String description, List<MenuItem> items) {
        super(menuSectionId);
        this.name = name;
        this.description = description;
        this.items = Objects.isNull(items) ? new ArrayList<>() : items;
    }

    public static MenuSection create(String name, String description, List<MenuItem> items) {
        return new MenuSection(MenuSectionId.createUnique(), name, description, items);
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
