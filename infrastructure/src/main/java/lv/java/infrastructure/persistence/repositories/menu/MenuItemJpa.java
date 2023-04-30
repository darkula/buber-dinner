package lv.java.infrastructure.persistence.repositories.menu;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Setter
@Accessors(chain = true)
@IdClass(MenuItemId.class)
@Table(name = "MENU_ITEM_T", schema = "BUBER_DINNER")
public class MenuItemJpa {

    @Id
    @Column(name = "MENU_ITEM_ID", nullable = false)
    private String menuItemId;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", length = 100, nullable = false)
    private String description;

    @Id
    @Column(name = "MENU_SECTION_ID", nullable = false)
    private String menuSectionId;

    @Id
    @Column(name = "MENU_ID", nullable = false)
    private String menuId;

}
