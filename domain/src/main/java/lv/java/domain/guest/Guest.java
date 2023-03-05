package lv.java.domain.guest;

import lv.java.domain.bill.value_objects.BillId;
import lv.java.domain.common.models.AggregateRoot;
import lv.java.domain.common.value_objects.AverageRating;
import lv.java.domain.common.value_objects.Rating;
import lv.java.domain.dinner.value_objects.DinnerId;
import lv.java.domain.guest.value_objects.GuestId;
import lv.java.domain.menu_review.value_objects.MenuReviewId;
import lv.java.domain.user.value_objects.UserId;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public final class Guest extends AggregateRoot<GuestId> {
    private String firstName;
    private String lastName;
    private URL profileImage;
    private AverageRating averageRating;
    private UserId userId;
    private final List<DinnerId> upcomingDinnerIds = new ArrayList<>();
    private final List<DinnerId> pastDinnerIds = new ArrayList<>();
    private final List<DinnerId> pendingDinnerIds = new ArrayList<>();
    private final List<BillId> billIds = new ArrayList<>();
    private final List<MenuReviewId> menuReviewIds = new ArrayList<>();
    private final List<Rating> ratings = new ArrayList<>();
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

    public Guest(GuestId id) {
        super(id);
    }
}
