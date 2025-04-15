package com.flightbooking.checkinservice.repository;

import com.flightbooking.checkinservice.entity.Checkin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CheckinRepository extends JpaRepository<Checkin, Long> {
    Optional<Checkin> findByBookingId(Long bookingId);
}