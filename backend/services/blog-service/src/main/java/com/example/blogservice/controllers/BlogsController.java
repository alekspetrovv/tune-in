package com.example.blogservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blogs")
public class BlogsController {

    @GetMapping("/send/user")
    public String sendMessage(){
        return "Hello User from Blog Service!";
    }
}
