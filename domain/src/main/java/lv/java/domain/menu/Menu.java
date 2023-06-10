package lv.java.domain.menu;

import lv.java.domain.common.models.AggregateRoot;
import lv.java.domain.dinner.value_objects.DinnerId;
import lv.java.domain.host.value_objects.HostId;
import lv.java.domain.menu.entities.MenuSection;
import lv.java.domain.menu.events.MenuCreated;
import lv.java.domain.menu.value_objects.MenuId;
import lv.java.domain.menu_review.value_objects.MenuReviewId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class Menu extends AggregateRoot<MenuId> {
    private String name;
    private String description;
    private BigDecimal averageRating;
    private final List<MenuSection> sections;
    private HostId hostId;
    private final List<DinnerId> dinnerIds = new ArrayList<>();
    private final List<MenuReviewId> menuReviewIds = new ArrayList<>();
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

    private Menu(MenuId menuId, String name, String description, HostId hostId, LocalDateTime createdDateTime, LocalDateTime updatedDateTime, List<MenuSection> sections) {
        super(menuId);
        this.name = name;
        this.description = description;
        this.hostId = hostId;
        this.createdDateTime = createdDateTime;
        this.updatedDateTime = updatedDateTime;
        this.sections = Objects.isNull(sections) ? new ArrayList<>() : sections;
        this.averageRating = BigDecimal.ZERO;
    }

    public static Menu create(HostId hostId, String name, String description, List<MenuSection> sections) {
        var menu = new Menu(MenuId.createUnique(), name, description, hostId, LocalDateTime.now(), LocalDateTime.now(), sections);

        menu.addDomainEvent(new MenuCreated(menu));
        return menu;
    }

    public List<MenuSection> getSections() {
        return Collections.unmodifiableList(sections);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getAverageRating() {
        return averageRating;
    }

    public HostId getHostId() {
        return hostId;
    }

    public List<DinnerId> getDinnerIds() {
        return Collections.unmodifiableList(dinnerIds);
    }

    public List<MenuReviewId> getMenuReviewIds() {
        return Collections.unmodifiableList(menuReviewIds);
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public LocalDateTime getUpdatedDateTime() {
        return updatedDateTime;
    }
}
