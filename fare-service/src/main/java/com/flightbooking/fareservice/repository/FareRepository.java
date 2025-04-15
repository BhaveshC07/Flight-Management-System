package com.flightbooking.fareservice.repository;

import com.flightbooking.fareservice.entity.Fare;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FareRepository extends JpaRepository<Fare, Long> {
    Optional<Fare> findByFlightNumber(String flightNumber);
}