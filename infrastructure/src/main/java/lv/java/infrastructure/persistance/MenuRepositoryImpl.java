package lv.java.infrastructure.persistance;

import lv.java.application.common.interfaces.persistance.MenuRepository;
import lv.java.domain.menu.Menu;

import java.util.ArrayList;
import java.util.List;

public class MenuRepositoryImpl implements MenuRepository {
    private final List<Menu> menus = new ArrayList<>();

    @Override
    public void add(Menu menu) {
        this.menus.add(menu);
    }
}
