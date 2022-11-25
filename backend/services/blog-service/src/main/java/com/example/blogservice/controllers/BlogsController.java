package com.example.blogservice.controllers;


import com.example.blogservice.configs.MQConfig;
import com.example.blogservice.models.CustomMessageDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blogs")
public class BlogsController {

    private RabbitTemplate template;

    @Autowired
    private BlogsController(RabbitTemplate template) {
        this.template = template;
    }

    @PostMapping("/test")
    public String publishMessage(@RequestBody CustomMessageDTO message) {
        message.setMessage(message.getMessage());
        message.setMessageDate(message.getMessageDate());
        template.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING, message);
        return "";
    }


}
