package com.flightbooking.checkinservice;


import com.flightbooking.checkinservice.entity.Booking;
import com.flightbooking.checkinservice.entity.Checkin;
import com.flightbooking.checkinservice.feign.BookingClient;
import com.flightbooking.checkinservice.repository.CheckinRepository;
import com.flightbooking.checkinservice.service.CheckinService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CheckinServiceTest {

    @Mock
    private CheckinRepository checkinRepository;

    @Mock
    private BookingClient bookingClient;

    @InjectMocks
    private CheckinService checkinService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCheckIn_Success() {
        // Arrange
        Long bookingId = 1L;
        Booking booking = new Booking("John", "AI123", "Pune", "Delhi", "2025-04-08", 2000.0, "BOOKED");
        booking.setId(bookingId); // set ID to match
        when(bookingClient.getBookingById(bookingId)).thenReturn(booking);

        // Act
        String result = checkinService.checkIn(bookingId);

        // Assert
        assertEquals("Check-in Successful for Booking ID: " + bookingId, result);
        verify(checkinRepository, times(1)).save(any(Checkin.class));
    }

    @Test
    void testCheckIn_BookingIdMismatch() {
        // Arrange
        Long bookingId = 1L;
        Booking booking = new Booking("John", "AI123", "Pune", "Delhi", "2025-04-08", 2000.0, "BOOKED");
        booking.setId(2L); // mismatch ID
        when(bookingClient.getBookingById(bookingId)).thenReturn(booking);

        // Act & Assert
        RuntimeException ex = assertThrows(RuntimeException.class, () -> checkinService.checkIn(bookingId));
        assertEquals("Booking Id Not found", ex.getMessage());
        verify(checkinRepository, never()).save(any(Checkin.class));
    }

    @Test
    void testCheckinStatus_CheckedIn() {
        // Arrange
        Long bookingId = 1L;
        Checkin checkin = new Checkin();
        checkin.setCheckedIn(true);
        checkin.setBookingId(bookingId);

        when(checkinRepository.findByBookingId(bookingId)).thenReturn(Optional.of(checkin));

        // Act
        String status = checkinService.checkinStatus(bookingId);

        // Assert
        assertEquals("Checked-in", status);
    }

    @Test
    void testCheckinStatus_NotCheckedIn() {
        Long bookingId = 2L;
        Checkin checkin = new Checkin();
        checkin.setCheckedIn(false);
        checkin.setBookingId(bookingId);

        when(checkinRepository.findByBookingId(bookingId)).thenReturn(Optional.of(checkin));

        String status = checkinService.checkinStatus(bookingId);

        assertEquals("Not Checked-in", status);
    }

    @Test
    void testCheckinStatus_BookingNotFound() {
        Long bookingId = 3L;

        when(checkinRepository.findByBookingId(bookingId)).thenReturn(Optional.empty());

        String status = checkinService.checkinStatus(bookingId);

        assertEquals("Booking Not Found", status);
    }
}
