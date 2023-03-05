package lv.java.domain.menu;

import lv.java.domain.common.models.AggregateRoot;
import lv.java.domain.dinner.value_objects.DinnerId;
import lv.java.domain.host.value_objects.HostId;
import lv.java.domain.menu.entities.MenuSection;
import lv.java.domain.menu.value_objects.MenuId;
import lv.java.domain.menu_review.value_objects.MenuReviewId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Menu extends AggregateRoot<MenuId> {
    private String name;
    private String description;
    private BigDecimal averageRating;
    private final List<MenuSection> sections = new ArrayList<>();
    private HostId hostId;
    private final List<DinnerId> dinnerIds = new ArrayList<>();
    private final List<MenuReviewId> menuReviewIds = new ArrayList<>();
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

    private Menu(MenuId menuId, String name, String description, HostId hostId, LocalDateTime createdDateTime, LocalDateTime updatedDateTime) {
        super(menuId);
        this.name = name;
        this.description = description;
        this.hostId = hostId;
        this.createdDateTime = createdDateTime;
        this.updatedDateTime = updatedDateTime;
    }

    public static Menu create(String name, String description, HostId hostId) {
        return new Menu(MenuId.createUnique(), name, description, hostId, LocalDateTime.now(), LocalDateTime.now());
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
}
