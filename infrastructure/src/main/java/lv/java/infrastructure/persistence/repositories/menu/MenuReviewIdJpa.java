package lv.java.infrastructure.persistence.repositories.menu;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Setter
@Accessors(chain = true)
@Table(name = "MENU_REVIEW_ID_T", schema = "BUBER_DINNER")
public class MenuReviewIdJpa {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MENU_REVIEW_ID", nullable = false)
    private String menuReviewId;

    @Column(name = "MENU_ID", nullable = false)
    private String menuId;

}
