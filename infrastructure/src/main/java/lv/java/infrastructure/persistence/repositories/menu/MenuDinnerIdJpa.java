package lv.java.infrastructure.persistence.repositories.menu;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Setter
@Accessors(chain = true)
@Table(name = "MENU_DINNER_ID_T", schema = "BUBER_DINNER")
public class MenuDinnerIdJpa {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DINNER_ID", nullable = false)
    private String dinnerId;

    @Column(name = "MENU_ID", nullable = false)
    private String menuId;

}
