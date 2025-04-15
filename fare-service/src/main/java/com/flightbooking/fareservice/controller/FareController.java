package com.flightbooking.fareservice.controller;

import com.flightbooking.fareservice.entity.Fare;
import com.flightbooking.fareservice.service.FareService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fare")
public class FareController {
    private final FareService fareService;

    public FareController(FareService fareService) {
        this.fareService = fareService;
    }

    @GetMapping("/{flightNumber}")
    public Fare getFare(@PathVariable String flightNumber) {
        return fareService.getFareByFlightNumber(flightNumber);
    }

    @PostMapping("/add")
    public Fare addFare(@RequestBody Fare fare) {
        return fareService.addFare(fare);
    }
}
