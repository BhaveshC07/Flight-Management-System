package com.flightbooking.checkinservice.dto;

import jakarta.validation.constraints.NotNull;

public class CheckinRequest {
	@NotNull(message = "Booking ID is required")
    private Long bookingId;

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }
}