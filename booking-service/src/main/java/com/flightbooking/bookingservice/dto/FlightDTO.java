package com.flightbooking.bookingservice.dto;

import jakarta.validation.constraints.NotBlank;

public class FlightDTO {
	@NotBlank(message = "Flight number is required")
    private String flightNumber;

    @NotBlank(message = "From location is required")
    private String fromLocation;

    @NotBlank(message = "To location is required")
    private String toLocation;

    @NotBlank(message = "Date is required")
    private String date;

    public FlightDTO() {}

    public FlightDTO(String flightNumber, String fromLocation, String toLocation, String date) {
        this.flightNumber = flightNumber;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.date = date;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

