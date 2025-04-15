package com.flightbooking.bookingservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Flight {

	@NotNull(message = "Flight ID is required")
    private Long id;

    @NotBlank(message = "Flight number is required")
    private String flightNumber;

    @NotBlank(message = "Airline is required")
    private String airline;

    @NotBlank(message = "Source is required")
    private String source;

    @NotBlank(message = "Destination is required")
    private String destination;

    @NotBlank(message = "Travel date is required")
    private String getTravelDate;

    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private double price;
    
    
    public Flight(String flightNumber, String source, String destination, String getTravelDate) {
        this.flightNumber = flightNumber;
        this.source = source;
        this.destination = destination;
        this.getTravelDate = getTravelDate;
    }

    

	public String getGetTravelDate() {
		return getTravelDate;
	}
	public void setGetTravelDate(String getTravelDate) {
		this.getTravelDate = getTravelDate;
	}


    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }

    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }


    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}