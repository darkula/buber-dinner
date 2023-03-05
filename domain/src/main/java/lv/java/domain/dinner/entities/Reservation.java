package lv.java.domain.dinner.entities;

import lv.java.domain.bill.value_objects.BillId;
import lv.java.domain.common.models.Entity;
import lv.java.domain.dinner.enums.ReservationStatus;
import lv.java.domain.dinner.value_objects.ReservationId;
import lv.java.domain.guest.value_objects.GuestId;

import java.time.LocalDateTime;

public final class Reservation extends Entity<ReservationId> {
    private int guestCount;
    private ReservationStatus reservationStatus;
    private GuestId guestId;
    private BillId billId;
    private LocalDateTime arrivalDateTime;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

    public Reservation(ReservationId id) {
        super(id);
    }
}
