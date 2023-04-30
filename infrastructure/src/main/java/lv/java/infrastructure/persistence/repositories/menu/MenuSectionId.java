package lv.java.infrastructure.persistence.repositories.menu;

import lombok.Data;

import java.io.Serializable;

@Data
public class MenuSectionId implements Serializable {
    private String menuSectionId;
    private String menuId;
}