package lv.java.domain.guest.entities;

import lv.java.domain.common.models.Entity;
import lv.java.domain.guest.value_objects.GuestRatingId;

public final class GuestRating extends Entity<GuestRatingId> {

    public GuestRating(GuestRatingId id) {
        super(id);
    }
}
