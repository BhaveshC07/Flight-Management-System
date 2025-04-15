package com.flightbooking.bookingservice.service;

import com.flightbooking.bookingservice.dto.*;
import com.flightbooking.bookingservice.entity.Booking;
import com.flightbooking.bookingservice.entity.Fare;
import com.flightbooking.bookingservice.entity.Flight;
import com.flightbooking.bookingservice.feign.FareClient;
import com.flightbooking.bookingservice.feign.FlightClient;
import com.flightbooking.bookingservice.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private BookingRepository bookingRepository;
    private final FlightClient flightClient;
    private final FareClient fareClient;
    
    

    public BookingService(BookingRepository bookingRepository, FlightClient flightClient, FareClient fareClient) {
		super();
		this.bookingRepository = bookingRepository;
		this.flightClient = flightClient;
		this.fareClient = fareClient;
	}

	public Booking createBooking(String passengerName,String source,String destination,String travelDate) {
		try {
		    List<Flight> flights = flightClient.searchFlights(source, destination, travelDate);
		    if (flights.isEmpty()) throw new RuntimeException("No flight available");

		    Flight selectedFlight = flights.get(0);
		    Fare fare = fareClient.getFare(selectedFlight.getFlightNumber());

		    Booking booking = new Booking(
		        passengerName,
		        selectedFlight.getFlightNumber(),
		        selectedFlight.getSource(),
		        selectedFlight.getDestination(),
		        travelDate, // fixed typo here
		        fare.getPrice(),
		        "BOOKED"
		    );

		    return bookingRepository.save(booking);
		} catch (Exception e) {
		    throw new RuntimeException("Error while booking flight: " + e.getMessage());
		}

    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public String cancelBooking(Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isPresent()) {
            booking.get().setStatus("CANCELED");
            bookingRepository.save(booking.get());
            return "Booking Canceled Successfully!";
        }
        return "Booking Not Found!";
    }
}
