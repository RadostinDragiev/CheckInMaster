package com.checkinmaster.repository;

import com.checkinmaster.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    List<Reservation> getByAndCheckInDateBeforeAndCheckOutDateAfter(LocalDate toDate, LocalDate fromDate);
}
