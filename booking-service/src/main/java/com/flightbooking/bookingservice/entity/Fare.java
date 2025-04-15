package com.flightbooking.bookingservice.entity;

public class Fare {
    private Long id;
    private String flightNumber;
    private String airline;
    private double price;

    
    
	public Fare(String flightNumber, double price) {
		super();
		this.flightNumber = flightNumber;
		this.price = price;
	}
	// Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }

    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}