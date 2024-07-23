package com.checkinmaster.web;

import com.checkinmaster.model.entity.dto.CreateReservationDto;
import com.checkinmaster.model.entity.view.DetailsReservationView;
import com.checkinmaster.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Void> createReservation(@RequestBody CreateReservationDto createReservationDto,
                                                  UriComponentsBuilder uriComponentsBuilder) {
        DetailsReservationView reservation = this.reservationService.createReservation(createReservationDto);

        return ResponseEntity.created(
                        uriComponentsBuilder.path("/reservation/{UUID}")
                                .buildAndExpand(reservation.getUuid())
                                .toUri())
                .build();
    }

    @GetMapping("/{reservationUUID}")
    public ResponseEntity<DetailsReservationView> getReservation(@PathVariable UUID reservationUUID) {
        return ResponseEntity.ok(this.reservationService.getReservationById(reservationUUID));
    }

    @DeleteMapping("/{reservationUUID}")
    public ResponseEntity<Void> deleteReservation(@PathVariable UUID reservationUUID) {
        this.reservationService.deleteReservation(reservationUUID);
        return ResponseEntity.ok().build();
    }
}
