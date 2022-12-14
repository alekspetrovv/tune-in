package com.example.commentservice.services;

import com.example.commentservice.configs.MQConfig;
import com.example.commentservice.models.Comment;
import com.example.commentservice.models.CommentDTO;
import com.example.commentservice.repository.CommentRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public CommentService(CommentRepository commentRepository, RabbitTemplate rabbitTemplate) {
        this.commentRepository = commentRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public Comment save(Authentication context, CommentDTO dto) {
        // get user id
        String userId = context.getName();
        // create comment for specific blog
        Date date = new Date();
        Comment comment = Comment.builder().comment(dto.getComment()).createdDate(date).blogId(dto.getBlogId()).userId(userId).build();
        // send comment to the queue
        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING, dto);
        commentRepository.save(comment);
        return comment;
    }

    public Comment update(String id, CommentDTO commentDTO) throws IllegalAccessException {
        Comment updatedComment = get(id);
        Date date = new Date();
        updatedComment.setComment(commentDTO.getComment());
        updatedComment.setCreatedDate(date);
        commentRepository.save(updatedComment);
        return updatedComment;
    }

    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    public Comment get(String id) throws IllegalAccessException {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()) {
            Comment existingComment = comment.get();
            return existingComment;
        }
        throw new IllegalAccessException("Comment not found");
    }

    public void delete(String id) throws IllegalAccessException {
        Comment existingComment = get(id);
        commentRepository.delete(existingComment);
    }

    public ResponseEntity<?> saveComment(CommentDTO commentDTO) {
        // get logged in user
        Authentication context = SecurityContextHolder.getContext().getAuthentication();
        try {
            // post comment for blog
            Comment comment = save(context, commentDTO);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } catch (Exception e) {
            // throw exception
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> updateComment(String commentId, CommentDTO dto) {
        try {
            // post comment for blog
            Comment comment = update(commentId, dto);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } catch (Exception e) {
            // throw exception
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> getComment(String commentId) {
        try {
            // post comment for blog
            Comment comment = get(commentId);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } catch (Exception e) {
            // throw exception
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }



    public void deleteAll() {
        commentRepository.deleteAll();
    }


}
