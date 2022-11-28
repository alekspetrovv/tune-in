package com.example.feedservice.controller;

import com.example.feedservice.configs.MQConfig;
import com.example.feedservice.models.CustomMessageDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feed")
public class FeedController {

    @RabbitListener(queues = MQConfig.QUEUE)
    @GetMapping
    public void receiveMessage(CustomMessageDTO customMessageDTO) {
        System.out.println(customMessageDTO);
    }

    @GetMapping("/test")
    public String test() {
        return "deployed";
    }
}
