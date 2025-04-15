package com.flightbooking.checkinservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.flightbooking.checkinservice.entity.Booking;



@FeignClient(name = "booking-service" )
public interface BookingClient {
    @GetMapping("/booking/{id}")
    Booking getBookingById(@PathVariable("id") Long id);
}
