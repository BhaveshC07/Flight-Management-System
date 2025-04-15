package com.flightbooking.searchservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.flightbooking.searchservice.entity.Fare;


@FeignClient(name = "fare-service")
public interface FareClient {
    @GetMapping("/fare/{flightNumber}")
    public Fare getFare(@PathVariable String flightNumber);
}
