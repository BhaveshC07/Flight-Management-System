package com.flightbooking.bookingservice.dto;

import jakarta.validation.constraints.NotBlank;

public class BookingRequest {
	@NotBlank(message = "Passenger name is required")
    private String passengerName;

    @NotBlank(message = "Source is required")
    private String source;

    @NotBlank(message = "Destination is required")
    private String destination;

    @NotBlank(message = "Travel date is required")
    private String traveldate;
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getTravelDate() {
		return traveldate;
	}
	public void setTravelDate(String traveldate) {
		this.traveldate = traveldate;
	}

    // Getters and Setters
}
