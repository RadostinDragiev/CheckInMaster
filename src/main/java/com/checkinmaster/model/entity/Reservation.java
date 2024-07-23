package com.checkinmaster.model.entity;

import com.checkinmaster.model.entity.enums.PaymentType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation extends BaseEntity {

    @ManyToMany
    @JoinTable(
            name = "reservation_room",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id")
    )
    private List<Room> rooms;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Guest guest;

    @Column(name = "check_in_date", nullable = false)
    private LocalDate checkInDate;

    @Column(name = "check_out_date", nullable = false)
    private LocalDate checkOutDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_tyoe", nullable = false)
    private PaymentType paymentType;

    @Column(name = "guests_count", nullable = false)
    private int guestsCount;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<Payment> payments;
}
