package com.flightbooking.searchservice.repository;

import com.flightbooking.searchservice.entity.Flight;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findBySourceAndDestinationAndTravelDate(String source, String destination, String travelDate);
    //Optional<Flight> findByFlightNumberAndTravelDate(String flightNumber, String travelDate);
}