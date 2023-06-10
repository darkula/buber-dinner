package lv.java.application.menus.events;

import an.awesome.pipelinr.Notification;
import lombok.extern.slf4j.Slf4j;
import lv.java.domain.menu.events.MenuCreated;

@Slf4j
public class MenuCreatedEventHandler implements Notification.Handler<MenuCreated> {

    @Override
    public void handle(MenuCreated notification) {
        log.info("Hello from menu created event handler!");
    }
}
