package com.flightbooking.fareservice.service;

import com.flightbooking.fareservice.entity.Fare;
import com.flightbooking.fareservice.repository.FareRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FareService {
    private final FareRepository fareRepository;

    public FareService(FareRepository fareRepository) {
        this.fareRepository = fareRepository;
    }

    public Fare getFareByFlightNumber(String flightNumber) {
        return fareRepository.findByFlightNumber(flightNumber)
                .orElseThrow(() -> new RuntimeException("Fare not found for Flight: " + flightNumber));
    }

    public Fare addFare(Fare fare) {
        return fareRepository.save(fare);
    }
}