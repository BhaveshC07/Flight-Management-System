package com.flightbooking.searchservice.service;

import com.flightbooking.searchservice.entity.Fare;
import com.flightbooking.searchservice.entity.Flight;
import com.flightbooking.searchservice.feign.FareClient;
import com.flightbooking.searchservice.repository.FlightRepository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FlightService {
    private FlightRepository flightRepository;
    private FareClient fareClient;

    

    public FlightService(FlightRepository flightRepository, FareClient fareClient) {
		super();
		this.flightRepository = flightRepository;
		this.fareClient = fareClient;
	}
    
    
    public Fare getFaretByFlightId(String flightNumber) {
    	return fareClient.getFare(flightNumber);
    }
    
	public List<Flight> searchFlights(String source, String destination, String travelDate) {
        return flightRepository.findBySourceAndDestinationAndTravelDate(source, destination, travelDate);
    }

    public Flight addFlight(Flight flight) {
        return flightRepository.save(flight);
    }
}