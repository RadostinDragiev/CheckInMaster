package com.checkinmaster.service;

import com.checkinmaster.model.entity.Reservation;
import com.checkinmaster.model.entity.dto.CreateReservationDto;
import com.checkinmaster.model.entity.view.DetailsReservationView;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ReservationService {

    DetailsReservationView createReservation(CreateReservationDto createReservationDto);

    DetailsReservationView getReservationById(UUID uuid);

    void deleteReservation(UUID uuid);

    List<Reservation> getRoomReservationForPeriod(LocalDate fromDate, LocalDate toDate, UUID roomUUID);
}
