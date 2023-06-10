package lv.java.domain.menu.events;

import lv.java.domain.common.models.DomainEvent;
import lv.java.domain.menu.Menu;

public record MenuCreated(Menu menu) implements DomainEvent {
}
