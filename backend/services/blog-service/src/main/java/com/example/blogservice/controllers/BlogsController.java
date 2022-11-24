package com.example.blogservice.controllers;

import com.example.blogservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blogs")
public class BlogsController {

    private UserService userService;

    @Autowired
    private BlogsController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/receive")
    public String sendMessage() {
        return this.userService.receiveMessage();
    }

    @GetMapping("/test")
    public String test() {
        return "test 2";
    }


}
