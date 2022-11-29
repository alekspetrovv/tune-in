package com.example.feedservice.controllers;

import com.example.feedservice.services.NewsFeedService;
import com.example.feedservice.configs.MQConfig;
import com.example.feedservice.models.Comment;
import com.example.feedservice.models.CommentDTO;
import com.example.feedservice.models.CustomMessageDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feed")
public class FeedController {

    private NewsFeedService newsFeedService;

    @Autowired
    public FeedController(NewsFeedService newsFeedService) {
        this.newsFeedService = newsFeedService;
    }

    @RabbitListener(queues = MQConfig.QUEUE)
    @GetMapping
    public void receiveMessage(CustomMessageDTO customMessageDTO) {
        System.out.println(customMessageDTO);
    }

    @GetMapping("/test")
    public String test() {
        return "deployed";
    }

    @GetMapping(value = "/comment/{id}")
    public Comment getComment(@PathVariable String id) {
        return newsFeedService.getCommentById(id);
    }

    @GetMapping(value = "/comments")
    public List<Comment> getAllComments() {
        return newsFeedService.getComments();
    }

    @PostMapping(value = "/comment")
    public void createComment(@RequestBody CommentDTO commentDTO) {
        newsFeedService.createComment(commentDTO);
    }

    @PutMapping(value = "/comment/{id}")
    public void updateComment(@PathVariable String id, @RequestBody CommentDTO commentDTO) {
        newsFeedService.updateComment(id, commentDTO);
    }

    @DeleteMapping(value = "/comment/{id}")
    public void deleteComment(@PathVariable String id) {
        newsFeedService.deleteComment(id);
    }

}
