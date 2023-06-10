package lv.java.domain.common.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Entity<T> implements HasDomainEvents {
    private final T id;
    private final List<DomainEvent> domainEvents = new ArrayList<>();

    public Entity(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }

    @Override
    public List<DomainEvent> getDomainEvents() {
        return Collections.unmodifiableList(this.domainEvents);
    }

    public void addDomainEvent(DomainEvent domainEvent) {
        if (Objects.nonNull(domainEvent)) {
            this.domainEvents.add(domainEvent);
        }
    }

    @Override
    public void clearDomainEvents() {
        this.domainEvents.clear();
    }
}
