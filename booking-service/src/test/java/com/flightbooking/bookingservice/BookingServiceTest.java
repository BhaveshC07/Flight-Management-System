package com.flightbooking.bookingservice;

import com.flightbooking.bookingservice.entity.Booking;
import com.flightbooking.bookingservice.entity.Fare;
import com.flightbooking.bookingservice.entity.Flight;
import com.flightbooking.bookingservice.feign.FareClient;
import com.flightbooking.bookingservice.feign.FlightClient;
import com.flightbooking.bookingservice.repository.BookingRepository;
import com.flightbooking.bookingservice.service.BookingService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private FlightClient flightClient;

    @Mock
    private FareClient fareClient;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createBooking_success() {
        // Arrange
        String name = "Alice";
        String src = "Pune", dest = "Delhi", date = "2025-04-08";

        Flight flight = new Flight("AI101", src, dest, date);
        Fare fare = new Fare("AI101", 3000.0);

        when(flightClient.searchFlights(src, dest, date)).thenReturn(Collections.singletonList(flight));
        when(fareClient.getFare(flight.getFlightNumber())).thenReturn(fare);

        Booking expectedBooking = new Booking(name, flight.getFlightNumber(), src, dest, date, fare.getPrice(), "BOOKED");

        when(bookingRepository.save(any(Booking.class))).thenReturn(expectedBooking);

        // Act
        Booking result = bookingService.createBooking(name, src, dest, date);

        // Assert
        assertNotNull(result);
        assertEquals("AI101", result.getFlightNumber());
        verify(bookingRepository).save(any(Booking.class));
    }

    @Test
    void createBooking_noFlightsAvailable() {
        when(flightClient.searchFlights(anyString(), anyString(), anyString()))
                .thenReturn(Collections.emptyList());

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                bookingService.createBooking("Bob", "Pune", "Mumbai", "2025-04-08"));

        assertTrue(ex.getMessage().contains("No flight available"));
    }

    @Test
    void createBooking_exceptionThrown() {
        when(flightClient.searchFlights(anyString(), anyString(), anyString()))
                .thenThrow(new RuntimeException("Service down"));

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                bookingService.createBooking("Charlie", "Chennai", "Goa", "2025-04-08"));

        assertTrue(ex.getMessage().contains("Error while booking flight"));
    }

    @Test
    void getAllBookings_returnsList() {
        List<Booking> bookings = Arrays.asList(
                new Booking("John", "F001", "Pune", "Delhi", "2025-04-08", 3000.0, "BOOKED"),
                new Booking("Jane", "F002", "Mumbai", "Goa", "2025-04-09", 2500.0, "BOOKED")
        );

        when(bookingRepository.findAll()).thenReturn(bookings);

        List<Booking> result = bookingService.getAllBookings();

        assertEquals(2, result.size());
    }

    @Test
    void getBookingById_found() {
        Booking booking = new Booking("Sam", "F003", "Delhi", "Kolkata", "2025-04-10", 2800.0, "BOOKED");
        booking.setId(1L);

        when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));

        Optional<Booking> result = bookingService.getBookingById(1L);

        assertTrue(result.isPresent());
        assertEquals("Sam", result.get().getPassengerName());
    }

    @Test
    void cancelBooking_success() {
        Booking booking = new Booking("Leo", "F004", "Bangalore", "Hyderabad", "2025-04-11", 2200.0, "BOOKED");
        booking.setId(5L);

        when(bookingRepository.findById(5L)).thenReturn(Optional.of(booking));

        String response = bookingService.cancelBooking(5L);

        assertEquals("Booking Canceled Successfully!", response);
        assertEquals("CANCELED", booking.getStatus());
        verify(bookingRepository).save(booking);
    }

    @Test
    void cancelBooking_notFound() {
        when(bookingRepository.findById(99L)).thenReturn(Optional.empty());

        String response = bookingService.cancelBooking(99L);

        assertEquals("Booking Not Found!", response);
    }
}
