package lv.java.domain.menu.entities;

import lv.java.domain.common.models.Entity;
import lv.java.domain.menu.value_objects.MenuItemId;

public final class MenuItem extends Entity<MenuItemId> {
    private final String name;
    private final String description;

    private MenuItem(MenuItemId id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }

    public static MenuItem create(String name, String description) {
        return new MenuItem(MenuItemId.createUnique(), name, description);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
