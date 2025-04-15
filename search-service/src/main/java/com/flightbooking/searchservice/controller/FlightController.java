package com.flightbooking.searchservice.controller;

import com.flightbooking.searchservice.entity.Fare;
import com.flightbooking.searchservice.entity.Flight;
import com.flightbooking.searchservice.service.FlightService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public List<Flight> searchFlights(@RequestParam String source, 
                                      @RequestParam String destination, 
                                      @RequestParam String travelDate) {
    	System.out.println("Search Request => source: " + source + ", destination: " + destination + ", date: " + travelDate);
        return flightService.searchFlights(source, destination, travelDate);
    }
    
    @GetMapping("/{flightNumber}")
    public Fare getFaretByFlightId(@PathVariable String flightNumber ) {
    	return flightService.getFaretByFlightId(flightNumber);
    }

    @PostMapping("/add")
    public Flight addFlight(@RequestBody Flight flight) {
        return flightService.addFlight(flight);
    }
}