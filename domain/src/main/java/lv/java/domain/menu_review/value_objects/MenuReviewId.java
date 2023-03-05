package lv.java.domain.menu_review.value_objects;

import lv.java.domain.common.models.ValueObject;

import java.util.UUID;

public final class MenuReviewId extends ValueObject {
    private final UUID value;

    private MenuReviewId(UUID value) {
        this.value = value;
    }

    public static MenuReviewId createUnique() {
        return new MenuReviewId(UUID.randomUUID());
    }

    public UUID getValue() {
        return value;
    }
}
