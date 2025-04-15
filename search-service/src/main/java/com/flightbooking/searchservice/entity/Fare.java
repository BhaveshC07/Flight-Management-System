package com.flightbooking.searchservice.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Fare {

    private Long id;
    @NotBlank(message = "Flight number is required")
    private String flightNumber;

    @NotBlank(message = "Airline name is required")
    private String airline;

    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private double price;

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