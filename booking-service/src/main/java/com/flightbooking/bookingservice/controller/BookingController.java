package com.flightbooking.bookingservice.controller;

import com.flightbooking.bookingservice.dto.BookingRequest;
import com.flightbooking.bookingservice.entity.Booking;
import com.flightbooking.bookingservice.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/create")
    public Booking createBooking(@RequestBody BookingRequest request) {
        return bookingService.createBooking(
            request.getPassengerName(),
            request.getSource(),
            request.getDestination(),
            request.getTravelDate()
        );
    }

    @GetMapping("/all")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }
    
    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
    }
    
//    @GetMapping("/{id}")
//    public Optional<Booking> getBookingById(@PathVariable Long id) {
//        return bookingService.getBookingById(id);
//    }
    
    @GetMapping
    public List<Booking> getALL() {
        return bookingService.getAllBookings();
    }
    @DeleteMapping("/cancel/{id}")
    public String cancelBooking(@PathVariable Long id) {
        return bookingService.cancelBooking(id);
    }
}
