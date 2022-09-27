package com.example.userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/user")
public class UserController {

    private RestTemplate restTemplate;

    @Autowired
    private UserController(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @GetMapping("/receive")
    public String receiveMessage(){
        String message = restTemplate.getForObject("http://blog-service/blogs/send/user", String.class);
        return message;
    }

}
