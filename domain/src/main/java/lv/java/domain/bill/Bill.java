package lv.java.domain.bill;

import lv.java.domain.bill.value_objects.BillId;
import lv.java.domain.common.models.AggregateRoot;
import lv.java.domain.dinner.value_objects.DinnerId;
import lv.java.domain.dinner.value_objects.Price;
import lv.java.domain.guest.value_objects.GuestId;
import lv.java.domain.host.value_objects.HostId;

import java.time.LocalDateTime;

public class Bill extends AggregateRoot<BillId> {
    private DinnerId dinnerId;
    private GuestId guestId;
    private HostId hostId;
    private Price price;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

    public Bill(BillId id) {
        super(id);
    }
}
