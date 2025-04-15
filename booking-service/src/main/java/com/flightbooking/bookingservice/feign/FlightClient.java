package com.flightbooking.bookingservice.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flightbooking.bookingservice.dto.FlightDTO;
import com.flightbooking.bookingservice.entity.Flight;



@FeignClient(name = "search-service")
public interface FlightClient {

    @GetMapping("/search")
    List<Flight> searchFlights(@RequestParam("source") String source,
                               @RequestParam("destination") String destination,
                               @RequestParam("travelDate") String travelDate);
}
