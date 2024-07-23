package com.checkinmaster.model.entity.view;

import com.checkinmaster.model.entity.enums.PaymentType;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailsReservationView {

    private UUID uuid;
    private int roomId;
    private DetailsGuestView guest;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private PaymentType paymentType;
    private int guestsCount;
}
