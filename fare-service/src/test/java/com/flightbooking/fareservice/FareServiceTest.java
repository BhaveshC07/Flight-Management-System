package com.flightbooking.fareservice;



import com.flightbooking.fareservice.entity.Fare;
import com.flightbooking.fareservice.repository.FareRepository;
import com.flightbooking.fareservice.service.FareService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FareServiceTest {

    @Mock
    private FareRepository fareRepository;

    @InjectMocks
    private FareService fareService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetFareByFlightNumber_Found() {
        // Arrange
        String flightNumber = "AI123";
        Fare fare = new Fare();
        fare.setFlightNumber(flightNumber);
        fare.setPrice(2500.0);

        when(fareRepository.findByFlightNumber(flightNumber)).thenReturn(Optional.of(fare));

        // Act
        Fare result = fareService.getFareByFlightNumber(flightNumber);

        // Assert
        assertNotNull(result);
        assertEquals(flightNumber, result.getFlightNumber());
        assertEquals(2500.0, result.getPrice());
        verify(fareRepository, times(1)).findByFlightNumber(flightNumber);
    }

    @Test
    void testGetFareByFlightNumber_NotFound() {
        // Arrange
        String flightNumber = "INVALID123";
        when(fareRepository.findByFlightNumber(flightNumber)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                fareService.getFareByFlightNumber(flightNumber));
        assertEquals("Fare not found for Flight: " + flightNumber, exception.getMessage());
        verify(fareRepository, times(1)).findByFlightNumber(flightNumber);
    }

    @Test
    void testAddFare() {
        // Arrange
        Fare fare = new Fare();
        fare.setFlightNumber("AI202");
        fare.setPrice(3000.0);

        when(fareRepository.save(fare)).thenReturn(fare);

        // Act
        Fare saved = fareService.addFare(fare);

        // Assert
        assertNotNull(saved);
        assertEquals("AI202", saved.getFlightNumber());
        assertEquals(3000.0, saved.getPrice());
        verify(fareRepository, times(1)).save(fare);
    }
}
