package com.example.feedservice.controllers;

import com.example.feedservice.services.CommentService;
import com.example.feedservice.models.Comment;
import com.example.feedservice.models.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feed")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService newsFeedService) {
        this.commentService = newsFeedService;
    }

    @GetMapping(value = "/comment/{id}")
    public Comment getComment(@PathVariable String id) throws IllegalAccessException{
        return commentService.get(id);
    }

    @GetMapping(value = "/comments")
    public List<Comment> getAllComments() {
        return commentService.getAll();
    }

    @PostMapping(value = "/comment/{blogId}")
    public ResponseEntity<?> submitComment(@PathVariable("blogId") String blogId, @RequestBody CommentDTO commentDTO) {
        ResponseEntity<?> newComment = commentService.saveComment(blogId, commentDTO);
        return newComment;
    }

    @PutMapping(value = "/comment/{id}")
    public ResponseEntity<?> updateSubmittedComment(@PathVariable("id") String id, @RequestBody CommentDTO commentDTO) {
        ResponseEntity<?> updateComment = commentService.updateComment(id, commentDTO);
        return updateComment;
    }

    @DeleteMapping(value = "/comment/{id}")
    public void deleteComment(@PathVariable String id) throws IllegalAccessException {
        commentService.delete(id);
    }

    @DeleteMapping(value = "/comments")
    public void deleteAllComments() {
        commentService.deleteAll();
    }
}
