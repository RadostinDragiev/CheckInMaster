package com.checkinmaster.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "guests")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Guest extends BaseUser {

    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL)
    private List<Payment> payments;
}
