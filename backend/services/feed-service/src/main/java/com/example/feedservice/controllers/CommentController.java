package com.example.feedservice.controllers;

import com.example.feedservice.services.CommentService;
import com.example.feedservice.models.Comment;
import com.example.feedservice.models.CommentDTO;
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
        ResponseEntity<?> retrievedComment = commentService.getComment(id);
        return retrievedComment;
    }

    @GetMapping(value = "/all")
    public List<Comment> getAllComments() {
        return commentService.getAll();
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitComment(@RequestBody CommentDTO commentDTO) {
        ResponseEntity<?> submittedComment = commentService.saveComment(commentDTO);
        return submittedComment;
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateSubmittedComment(@PathVariable("id") String id, @RequestBody CommentDTO commentDTO) {
        ResponseEntity<?> updatedComment = commentService.updateComment(id, commentDTO);
        return updatedComment;
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
