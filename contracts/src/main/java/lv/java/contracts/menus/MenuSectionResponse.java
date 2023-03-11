package lv.java.contracts.menus;

import java.util.List;

public record MenuSectionResponse(
        String id,
        String name,
        String description,
        List<MenuItemResponse> items
) {

}
