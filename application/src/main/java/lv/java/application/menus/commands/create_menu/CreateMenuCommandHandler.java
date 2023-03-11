package lv.java.application.menus.commands.create_menu;

import an.awesome.pipelinr.Command;
import lv.java.application.common.interfaces.persistance.MenuRepository;
import lv.java.domain.common.error.Result;
import lv.java.domain.host.value_objects.HostId;
import lv.java.domain.menu.Menu;
import lv.java.domain.menu.entities.MenuItem;
import lv.java.domain.menu.entities.MenuSection;

import java.util.Objects;

public class CreateMenuCommandHandler implements Command.Handler<CreateMenuCommand, Result<Menu>> {
    private final MenuRepository menuRepository;

    public CreateMenuCommandHandler(MenuRepository menuRepository) {
        this.menuRepository = Objects.requireNonNull(menuRepository);
    }

    @Override
    public Result<Menu> handle(CreateMenuCommand command) {
        var menu = Menu.create(
                HostId.create(command.hostId()),
                command.name(),
                command.description(),
                command.sections()
                        .stream()
                        .map(s -> MenuSection.create(s.name(), s.description(), s.items()
                                .stream()
                                .map(i -> MenuItem.create(i.name(), i.description()))
                                .toList()))
                        .toList());

        this.menuRepository.add(menu);

        return Result.success(menu);
    }
}
