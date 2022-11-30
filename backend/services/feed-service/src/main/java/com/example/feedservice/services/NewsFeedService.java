package com.example.feedservice.services;

import com.example.feedservice.models.Comment;
import com.example.feedservice.models.CommentDTO;
import com.example.feedservice.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NewsFeedService {

    private CommentRepository commentRepository;

    @Autowired
    public NewsFeedService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(CommentDTO dto) {
        Date date = new Date();
        Comment comment = Comment.builder()
                .content(dto.getContent())
                .createdDate(date)
                .build();
        commentRepository.save(comment);
        return comment;
    }

    public Comment updateComment(String id, CommentDTO dto) {
        Optional<Comment> comment = commentRepository.findById(id);
        Comment updatedComment = comment.stream().findFirst().orElse(null);
        Date date = new Date();
        updatedComment.setContent(dto.getContent());
        updatedComment.setCreatedDate(date);
        commentRepository.save(updatedComment);
        return updatedComment;
    }

    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    public Comment getCommentById(String id) {
        Optional<Comment> comment = commentRepository.findById(id);
        Comment existingComment = comment.stream().findFirst().orElse(null);
        return existingComment;
    }

    public void deleteComment(String id) {
        Optional<Comment> comment = commentRepository.findById(id);
        Comment existingComment = comment.stream().findFirst().orElse(null);
        commentRepository.delete(existingComment);
    }


}
