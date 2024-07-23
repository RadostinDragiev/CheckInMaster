package com.checkinmaster.service;

import com.checkinmaster.model.entity.dto.CreateReservationDto;
import com.checkinmaster.model.entity.view.DetailsReservationView;

import java.util.UUID;

public interface ReservationService {

    DetailsReservationView createReservation(CreateReservationDto createReservationDto);

    DetailsReservationView getReservationById(UUID uuid);

    void deleteReservation(UUID uuid);
}
