package com.example.blogservice.services;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    private RestTemplate restTemplate;
    private int counter;

    @Autowired
    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "userService", fallbackMethod = "receiveMessageFallBack")
    public String receiveMessage() {
        // calling the user-service to get the message
        String message = restTemplate
                .getForObject("http://user-service/user/send", String.class);
        return message;
    }

    // fallback method when user service stop
    public String receiveMessageFallBack(Exception e) {
        return "fallback method invoked when User Service stops";
    }

}
