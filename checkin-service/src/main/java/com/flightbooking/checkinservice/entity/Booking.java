package com.flightbooking.checkinservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Booking {
    
    private Long id;
    private String passengerName;
    private String flightNumber;
    private String source;
    private String destination;
    private String travelDate;
    private double price;
    private String status;  // BOOKED, CANCELED
    

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