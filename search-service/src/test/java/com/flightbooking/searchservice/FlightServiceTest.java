package com.flightbooking.searchservice;


import com.flightbooking.searchservice.entity.Fare;
import com.flightbooking.searchservice.entity.Flight;
import com.flightbooking.searchservice.feign.FareClient;
import com.flightbooking.searchservice.repository.FlightRepository;
import com.flightbooking.searchservice.service.FlightService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FlightServiceTest {

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private FareClient fareClient;

    @InjectMocks
    private FlightService flightService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchFlights() {
        Flight flight = new Flight();
        flight.setFlightNumber("AI101");
        flight.setSource("Delhi");
        flight.setDestination("Mumbai");
        flight.setTravelDate("2025-04-15");

        when(flightRepository.findBySourceAndDestinationAndTravelDate("Delhi", "Mumbai", "2025-04-15"))
                .thenReturn(List.of(flight));

        List<Flight> flights = flightService.searchFlights("Delhi", "Mumbai", "2025-04-15");

        assertEquals(1, flights.size());
        assertEquals("AI101", flights.get(0).getFlightNumber());
        verify(flightRepository, times(1)).findBySourceAndDestinationAndTravelDate("Delhi", "Mumbai", "2025-04-15");
    }

    @Test
    void testAddFlight() {
        Flight flight = new Flight();
        flight.setFlightNumber("AI202");

        when(flightRepository.save(flight)).thenReturn(flight);

        Flight saved = flightService.addFlight(flight);

        assertNotNull(saved);
        assertEquals("AI202", saved.getFlightNumber());
        verify(flightRepository, times(1)).save(flight);
    }

    @Test
    void testGetFaretByFlightId() {
        Fare fare = new Fare();
        fare.setFlightNumber("AI101");
        fare.setPrice(5000.0);

        when(fareClient.getFare("AI101")).thenReturn(fare);

        Fare result = flightService.getFaretByFlightId("AI101");

        assertNotNull(result);
        assertEquals(5000.0, result.getPrice());
        verify(fareClient, times(1)).getFare("AI101");
    }
}