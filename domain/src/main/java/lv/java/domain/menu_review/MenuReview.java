package lv.java.domain.menu_review;

import lv.java.domain.common.models.AggregateRoot;
import lv.java.domain.common.value_objects.Rating;
import lv.java.domain.dinner.value_objects.DinnerId;
import lv.java.domain.guest.value_objects.GuestId;
import lv.java.domain.host.value_objects.HostId;
import lv.java.domain.menu.value_objects.MenuId;
import lv.java.domain.menu_review.value_objects.MenuReviewId;

import java.time.LocalDateTime;

public final class MenuReview extends AggregateRoot<MenuReviewId> {
    private Rating rating;
    private String comment;
    private HostId hostId;
    private MenuId menuId;
    private GuestId guestId;
    private DinnerId dinnerId;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

    public MenuReview(MenuReviewId id) {
        super(id);
    }
}
