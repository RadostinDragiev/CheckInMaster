package com.checkinmaster.model.entity;

import com.checkinmaster.model.entity.enums.RoomStatus;
import com.checkinmaster.model.entity.enums.RoomType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "rooms")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room extends BaseEntity {

    @Column(name = "number", nullable = false, unique = true)
    private int number;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type", nullable = false)
    private RoomType roomType;

    @Column(name = "price_per_night", nullable = false)
    private BigDecimal pricePerNight;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RoomStatus status;

    @ManyToMany(mappedBy = "rooms", cascade = CascadeType.ALL)
    private List<Reservation> reservations;
}
