package lv.java.infrastructure.persistence.repositories.menu;

import lombok.Data;

import java.io.Serializable;

@Data
public class MenuItemId implements Serializable {
    private String menuItemId;
    private String menuSectionId;
    private String menuId;
}