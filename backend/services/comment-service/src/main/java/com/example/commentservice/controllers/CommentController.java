package com.example.commentservice.controllers;

import com.example.commentservice.services.CommentService;
import com.example.commentservice.models.Comment;
import com.example.commentservice.models.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService newsFeedService) {
        this.commentService = newsFeedService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getComment(@PathVariable String id){
        return commentService.getComment(id);
    }

    @GetMapping(value = "/all")
    public List<Comment> getAllComments() {
        return commentService.getAll();
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitComment(@RequestBody CommentDTO commentDTO) {
      return commentService.saveComment(commentDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateSubmittedComment(@PathVariable("id") String id, @RequestBody CommentDTO commentDTO) {
       return commentService.updateComment(id, commentDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteComment(@PathVariable String id) throws IllegalAccessException {
        commentService.delete(id);
    }

    @DeleteMapping(value = "/all")
    public void deleteAllComments() {
        commentService.deleteAll();
    }
}
