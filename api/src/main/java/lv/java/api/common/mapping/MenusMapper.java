package lv.java.api.common.mapping;

import lv.java.application.menus.commands.create_menu.CreateMenuCommand;
import lv.java.application.menus.commands.create_menu.MenuItemCommand;
import lv.java.application.menus.commands.create_menu.MenuSectionCommand;
import lv.java.contracts.menus.CreateMenuRequest;
import lv.java.contracts.menus.MenuItemResponse;
import lv.java.contracts.menus.MenuResponse;
import lv.java.contracts.menus.MenuSectionResponse;
import lv.java.domain.dinner.value_objects.DinnerId;
import lv.java.domain.menu.Menu;
import lv.java.domain.menu_review.value_objects.MenuReviewId;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

public final class MenusMapper {

    public static CreateMenuCommand mapToCreateMenuCommand(CreateMenuRequest request, String hostId) {
        return new CreateMenuCommand(
                hostId,
                request.name(),
                request.description(),
                Optional.ofNullable(request.sections())
                        .orElseGet(ArrayList::new)
                        .stream()
                        .map(m -> new MenuSectionCommand(
                                m.name(),
                                m.description(),
                                Optional.ofNullable(m.items())
                                        .orElseGet(ArrayList::new)
                                        .stream()
                                        .map(i -> new MenuItemCommand(i.name(), i.description()))
                                        .toList()))
                        .toList()
        );
    }

    public static MenuResponse mapToMenuResponse(Menu menu) {
        return new MenuResponse(
                menu.getId().getValue(),
                menu.getName(),
                menu.getDescription(),
                Optional.ofNullable(menu.getAverageRating()).orElse(BigDecimal.ZERO),
                menu.getSections()
                        .stream()
                        .map(m -> new MenuSectionResponse(
                                m.getId().getValue(),
                                m.getName(),
                                m.getDescription(),
                                m.getItems()
                                        .stream()
                                        .map(i -> new MenuItemResponse(
                                                i.getId().getValue(),
                                                i.getName(),
                                                i.getDescription()))
                                        .toList()))
                        .toList(),
                menu.getHostId().getValue(),
                menu.getDinnerIds().stream().map(DinnerId::getValue).toList(),
                menu.getMenuReviewIds().stream().map(MenuReviewId::getValue).toList(),
                menu.getCreatedDateTime(),
                menu.getUpdatedDateTime()
        );
    }

    private MenusMapper() throws IllegalAccessException {
        throw new IllegalAccessException();
    }
}
