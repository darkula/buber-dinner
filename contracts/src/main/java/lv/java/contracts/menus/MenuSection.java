package lv.java.contracts.menus;

import java.util.List;

public record MenuSection(
        String name,
        String description,
        List<MenuItem> items
) {
}
