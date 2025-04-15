package com.flightbooking.checkinservice.service;

import com.flightbooking.checkinservice.entity.Booking;
import com.flightbooking.checkinservice.entity.Checkin;
import com.flightbooking.checkinservice.feign.BookingClient;
import com.flightbooking.checkinservice.repository.CheckinRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CheckinService {
    private  CheckinRepository checkinRepository;
    private BookingClient bookingClient;
    
    

    public CheckinService(CheckinRepository checkinRepository, BookingClient bookingClient) {
		super();
		this.checkinRepository = checkinRepository;
		this.bookingClient = bookingClient;
	}

    public String checkIn(Long bookingId) {
        Booking booking = bookingClient.getBookingById(bookingId);
        Checkin checkin = new Checkin();
        if (booking.getId() == bookingId) {
            checkin.setBookingId(bookingId);
            checkin.setCheckedIn(true);
            checkinRepository.save(checkin);
            return "Check-in Successful for Booking ID: " + bookingId;
        } else {
            throw new RuntimeException("Booking Id Not found");
        }
    }

    public String checkinStatus(Long bookingId) {
        Optional<Checkin> checkin = checkinRepository.findByBookingId(bookingId);
        return checkin.map(c -> c.isCheckedIn() ? "Checked-in" : "Not Checked-in")
                      .orElse("Booking Not Found");
    }
}