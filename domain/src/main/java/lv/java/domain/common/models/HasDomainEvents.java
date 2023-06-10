package lv.java.domain.common.models;

import java.util.List;

public interface HasDomainEvents {
    List<DomainEvent> getDomainEvents();

    void clearDomainEvents();
}
