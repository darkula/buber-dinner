package lv.java.contracts.menus;

import java.util.List;

public record CreateMenuRequest(
        String name,
        String description,
        List<MenuSection> sections
) {
}
