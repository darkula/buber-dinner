package lv.java.infrastructure.persistence.repositories.menu;

import an.awesome.pipelinr.Pipeline;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lv.java.application.common.interfaces.persistance.MenuRepository;
import lv.java.domain.common.models.DomainEvent;
import lv.java.domain.dinner.value_objects.DinnerId;
import lv.java.domain.menu.Menu;
import lv.java.domain.menu.entities.MenuItem;
import lv.java.domain.menu.entities.MenuSection;
import lv.java.domain.menu_review.value_objects.MenuReviewId;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class MenuRepositoryImpl implements MenuRepository {

    @PersistenceContext
    private EntityManager entityManager;
    private final Pipeline pipeline;

    public MenuRepositoryImpl(@Qualifier("notificationHandlers") Pipeline pipeline) {
        this.pipeline = Objects.requireNonNull(pipeline);
    }

    @Override
    @Transactional
    public void add(Menu menu) {
        MenuJpa menuJpa = new MenuJpa()
                .setId(menu.getId().getValue())
                .setName(menu.getName())
                .setDescription(menu.getDescription())
                .setAverageRatingValue(menu.getAverageRating())
                .setHostId(menu.getHostId().getValue())
                .setCreatedDateTime(menu.getCreatedDateTime())
                .setUpdateDateTime(menu.getUpdatedDateTime());

        this.entityManager.persist(menuJpa);

        for (MenuSection menuSection : menu.getSections()) {
            MenuSectionJpa menuSectionJpa = new MenuSectionJpa()
                    .setName(menuSection.getName())
                    .setDescription(menuSection.getDescription())
                    .setMenuSectionId(menuSection.getId().getValue())
                    .setMenuId(menu.getId().getValue());
            this.entityManager.persist(menuSectionJpa);

            for (MenuItem menuItem : menuSection.getItems()) {
                MenuItemJpa menuItemJpa = new MenuItemJpa()
                        .setName(menuItem.getName())
                        .setDescription(menuItem.getDescription())
                        .setMenuItemId(menuItem.getId().getValue())
                        .setMenuSectionId(menuSection.getId().getValue())
                        .setMenuId(menu.getId().getValue());
                this.entityManager.persist(menuItemJpa);
            }
        }

        for (DinnerId dinnerId : menu.getDinnerIds()) {
            MenuDinnerIdJpa menuDinnerIdJpa = new MenuDinnerIdJpa()
                    .setMenuId(menu.getId().getValue())
                    .setDinnerId(dinnerId.getValue());
            this.entityManager.persist(menuDinnerIdJpa);
        }

        for (MenuReviewId menuReviewId : menu.getMenuReviewIds()) {
            MenuReviewIdJpa menuReviewIdJpa = new MenuReviewIdJpa()
                    .setMenuId(menu.getId().getValue())
                    .setMenuReviewId(menuReviewId.getValue());
            this.entityManager.persist(menuReviewIdJpa);
        }

        List<DomainEvent> domainEvents = new ArrayList<>(menu.getDomainEvents());

        menu.clearDomainEvents();

        for (DomainEvent event : domainEvents) {
            //Not good, because event happens before transaction, in case of transaction failure false event would be sent.
            event.send(this.pipeline);
        }
    }
}
