package com.checkinmaster.service.impl;

import com.checkinmaster.model.entity.Guest;
import com.checkinmaster.model.entity.Reservation;
import com.checkinmaster.model.entity.dto.CreateReservationDto;
import com.checkinmaster.model.entity.view.DetailsReservationView;
import com.checkinmaster.repository.ReservationRepository;
import com.checkinmaster.service.GuestService;
import com.checkinmaster.service.ReservationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ModelMapper modelMapper;
    private final GuestService guestService;

    @Override
    @Transactional
    public DetailsReservationView createReservation(CreateReservationDto createReservationDto) {
        // TODO: Check room availability
        Reservation reservation = this.modelMapper.map(createReservationDto, Reservation.class);

        Guest guest = this.guestService.findGuest(createReservationDto.getGuest());
        reservation.setGuest(guest);

        Reservation savedReservation = this.reservationRepository.saveAndFlush(reservation);

        return this.modelMapper.map(savedReservation, DetailsReservationView.class);
    }

    @Override
    public DetailsReservationView getReservationById(UUID uuid) {
        return this.modelMapper.map(this.reservationRepository.findById(uuid), DetailsReservationView.class);
    }

    @Override
    public void deleteReservation(UUID uuid) {
        this.reservationRepository.deleteById(uuid);
    }
}
