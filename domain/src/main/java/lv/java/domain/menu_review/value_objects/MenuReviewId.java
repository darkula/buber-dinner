package lv.java.domain.menu_review.value_objects;

import lv.java.domain.common.models.ValueObject;

import java.util.UUID;

public final class MenuReviewId extends ValueObject {
    private final String value;

    private MenuReviewId(String value) {
        this.value = value;
    }

    public static MenuReviewId createUnique() {
        return new MenuReviewId(UUID.randomUUID().toString());
    }

    public String getValue() {
        return value;
    }
}
