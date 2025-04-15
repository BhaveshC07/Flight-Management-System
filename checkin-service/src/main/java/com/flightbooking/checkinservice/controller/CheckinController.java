package com.flightbooking.checkinservice.controller;

import com.flightbooking.checkinservice.dto.CheckinRequest;
import com.flightbooking.checkinservice.service.CheckinService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkin")
public class CheckinController {
    private final CheckinService checkinService;

    public CheckinController(CheckinService checkinService) {
        this.checkinService = checkinService;
    }

    @PostMapping("/doCheckin")
    public String checkIn(@RequestBody CheckinRequest request) {
        return checkinService.checkIn(request.getBookingId());
    }


    @GetMapping("/status/{bookingId}")
    public String checkinStatus(@PathVariable Long bookingId) {
        return checkinService.checkinStatus(bookingId);
    }
}