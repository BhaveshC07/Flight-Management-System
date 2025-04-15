package com.flightbooking.bookingservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Passenger name is required")
    private String passengerName;

    @NotBlank(message = "Flight number is required")
    private String flightNumber;

    @NotBlank(message = "Source is required")
    private String source;

    @NotBlank(message = "Destination is required")
    private String destination;

    @NotBlank(message = "Travel date is required")
    private String travelDate;

    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private double price;

    @NotBlank(message = "Status is required")
    private String status;
    

	    public Booking(String passengerName, String flightNumber, String source, String destination,
			String travelDate, double price, String status) {
		super();
		this.passengerName = passengerName;
		this.flightNumber = flightNumber;
		this.source = source;
		this.destination = destination;
		this.travelDate = travelDate;
		this.price = price;
		this.status = status;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	// Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPassengerName() { return passengerName; }
    public void setPassengerName(String passengerName) { this.passengerName = passengerName; }

    public Booking() {};

    
    public Booking(Long id, String passengerName, String flightNumber, String source, String destination,
			String travelDate, double price, String status) {
		super();
		this.id = id;
		this.passengerName = passengerName;
		this.flightNumber = flightNumber;
		this.source = source;
		this.destination = destination;
		this.travelDate = travelDate;
		this.price = price;
		this.status = status;
	}
	public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getTravelDate() { return travelDate; }
    public void setTravelDate(String travelDate) { this.travelDate = travelDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}