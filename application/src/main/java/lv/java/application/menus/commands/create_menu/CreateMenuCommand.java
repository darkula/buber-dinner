package lv.java.application.menus.commands.create_menu;

import an.awesome.pipelinr.Command;
import lv.java.domain.common.error.Result;
import lv.java.domain.menu.Menu;

import java.util.List;

public record CreateMenuCommand(
        String hostId,
        String name,
        String description,
        List<MenuSectionCommand> sections
) implements Command<Result<Menu>> {
}