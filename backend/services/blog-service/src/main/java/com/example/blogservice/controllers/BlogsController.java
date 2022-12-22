package com.example.blogservice.controllers;

import com.example.blogservice.configs.MQConfig;
import com.example.blogservice.models.Blog;
import com.example.blogservice.models.BlogDTO;
import com.example.blogservice.models.CommentDTO;
import com.example.blogservice.services.BlogService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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


    @RabbitListener(queues = MQConfig.QUEUE)
    public void receiveComments(CommentDTO commentDTO) {
        blogService.assignComments(commentDTO);
    }

    @GetMapping("/{id}")
    public Blog getBlog(@PathVariable String id) throws IllegalAccessException {
        return blogService.getById(id);
    }

    @GetMapping("/all")
    public List<Blog> getBlogs() {
        return blogService.getAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody BlogDTO blogDTO) {
      return blogService.saveBlog(blogDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody BlogDTO blogDTO) {
       return blogService.updateBlog(id, blogDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) throws IllegalAccessException {
        blogService.delete(id);
    }

    @DeleteMapping(value = "/all")
    public void deleteAll() {
        blogService.deleteAll();
    }
}
