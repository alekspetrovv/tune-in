package com.example.blogservice.controllers;

import com.example.blogservice.configs.MQConfig;
import com.example.blogservice.models.Blog;
import com.example.blogservice.models.BlogDTO;
import com.example.blogservice.services.BlogService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogsController {


    private final BlogService blogService;

    @Autowired
    private BlogsController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping(value = "/{id}")
    public Blog getBlog(@PathVariable String id) {
        return blogService.getById(id);
    }

    @GetMapping(value = "/all")
    public List<Blog> getBlogs() {
        return blogService.getAll();
    }

    @PostMapping(value = "/")
    public void create(@RequestBody BlogDTO blogDTO) {
        blogService.create(blogDTO);
    }

    @PutMapping(value = "/{id}")
    public void update(@PathVariable String id, @RequestBody BlogDTO blogDTO) {
        blogService.update(id, blogDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable String id) {
        blogService.delete(id);
    }

    @GetMapping("/test")
    @RabbitListener(queues = MQConfig.QUEUE)
    public String test() {
        return "deployed";
    }

}
