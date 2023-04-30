package lv.java.infrastructure.persistence.repositories.menu;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Setter
@Accessors(chain = true)
@Table(name = "MENU_T", schema = "BUBER_DINNER")
public class MenuJpa {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", length = 100, nullable = false)
    private String description;

    @Column(name = "AVERAGE_RATING_VALUE", nullable = false)
    private BigDecimal averageRatingValue;

    @Column(name = "HOST_ID", length = 100, nullable = false)
    private String hostId;

    @Column(name = "CREATION_DATE_TIME", nullable = false)
    private LocalDateTime createdDateTime;

    @Column(name = "UPDATE_DATE_TIME", nullable = false)
    private LocalDateTime updateDateTime;

}
