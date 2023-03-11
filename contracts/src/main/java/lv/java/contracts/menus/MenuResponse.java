package lv.java.contracts.menus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record MenuResponse(
        String id,
        String name,
        String description,
        BigDecimal averageRating,
        List<MenuSectionResponse> sections,
        String hostId,
        List<String> dinnerIds,
        List<String> menuReviewIds,
        LocalDateTime createdDateTime,
        LocalDateTime updatedDateTime
) {
}



