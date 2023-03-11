package lv.java.application.menus.commands.create_menu;

import java.util.List;

public record MenuSectionCommand(
        String name,
        String description,
        List<MenuItemCommand> items
) {
}
