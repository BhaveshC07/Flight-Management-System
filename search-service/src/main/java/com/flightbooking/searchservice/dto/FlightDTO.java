//package com.flightbooking.searchservice.dto;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//
//public class FlightDTO {
//	
//    private Long id;
//    private String flightNumber;
//    private String airline;
//    private String source;
//    private String destination;
//    private String travelDate;
//    private double price;
//
//    public String getTravelDate() {
//		return travelDate;
//	}
//	public void setTravelDate(String travelDate) {
//		this.travelDate = travelDate;
//	}
//	// Getters and Setters
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    public String getFlightNumber() { return flightNumber; }
//    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }
//
//    public String getAirline() { return airline; }
//    public void setAirline(String airline) { this.airline = airline; }
//
//    public String getSource() { return source; }
//    public void setSource(String source) { this.source = source; }
//
//    public String getDestination() { return destination; }
//    public void setDestination(String destination) { this.destination = destination; }
//
////    public String getDepartureDate() { return departureDate; }
////    public void setDepartureDate(String departureDate) { this.departureDate = departureDate; }
//
//    public double getPrice() { return price; }
//    public void setPrice(double price) { this.price = price; }
//}