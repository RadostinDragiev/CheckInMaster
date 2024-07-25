package com.checkinmaster.model.entity.view;

import com.checkinmaster.model.entity.enums.PaymentMethod;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentView {

    private UUID uuid;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private UUID guestId;
    private UUID reservationId;
    private LocalDateTime paymentDate;
}
