package lv.java.domain.host;

import lv.java.domain.common.models.AggregateRoot;
import lv.java.domain.common.value_objects.AverageRating;
import lv.java.domain.dinner.value_objects.DinnerId;
import lv.java.domain.host.value_objects.HostId;
import lv.java.domain.menu.value_objects.MenuId;
import lv.java.domain.user.value_objects.UserId;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public final class Host extends AggregateRoot<HostId> {
    private String firstName;
    private String lastName;
    private URL profileImage;
    private AverageRating averageRating;
    private UserId userId;
    private final List<MenuId> menuIds = new ArrayList<>();
    private final List<DinnerId> dinnerIds = new ArrayList<>();
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

    public Host(HostId id) {
        super(id);
    }
}
