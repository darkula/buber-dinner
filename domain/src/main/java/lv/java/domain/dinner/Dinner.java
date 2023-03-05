package lv.java.domain.dinner;

import lv.java.domain.common.models.AggregateRoot;
import lv.java.domain.dinner.entities.Reservation;
import lv.java.domain.dinner.enums.DinnerStatus;
import lv.java.domain.dinner.value_objects.DinnerId;
import lv.java.domain.dinner.value_objects.Location;
import lv.java.domain.dinner.value_objects.Price;
import lv.java.domain.host.value_objects.HostId;
import lv.java.domain.menu.value_objects.MenuId;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public final class Dinner extends AggregateRoot<DinnerId> {
    private String name;
    private String description;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private LocalDateTime startedDateTime;
    private LocalDateTime endedDateTime;
    private DinnerStatus status;
    private boolean isPublic;
    private int maxGuests;
    private Price price;
    private HostId hostId;
    private MenuId menuId;
    private URL imageUrl;
    private Location location;
    private final List<Reservation> reservations = new ArrayList<>();
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

    public Dinner(DinnerId id) {
        super(id);
    }
}
