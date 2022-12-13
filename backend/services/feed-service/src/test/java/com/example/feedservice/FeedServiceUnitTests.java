package com.example.feedservice;

import com.example.feedservice.models.Comment;
import com.example.feedservice.models.CommentDTO;
import com.example.feedservice.services.CommentService;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class FeedServiceUnitTests {
    @MockBean
    private CommentService commentService;
    private Comment comment;
    private CommentDTO commentDTO;
    private Comment commentOne;
    private Comment commentTwo;
    private List<Comment> comments;
    private Date date;

    @BeforeEach
    public void setup() {
        date = new Date();
        comment = new Comment();
        comment.setId("1");
        comment.setComment("cool blog, I like this game");
        comment.setCreatedDate(date);
        commentDTO = new CommentDTO();
        commentDTO.setComment("cool blog, I like this game");
        commentDTO.setCreatedDate(date);
        commentOne = new Comment();
        commentOne.setId("2");
        commentOne.setComment("cool blog, I like this game, where can i buy it?");
        commentOne.setCreatedDate(date);
        commentTwo = new Comment();
        commentTwo.setId("3");
        commentTwo.setComment("cool blog, I like this game, where can i buy it, nice nice?");
        commentTwo.setCreatedDate(date);
    }

    @Test
    void createComment() {
        Authentication context = SecurityContextHolder.getContext().getAuthentication();
        // create comment
        when(commentService.save("1",  context,  commentDTO)).thenReturn(comment);
        // assert
        assertNotNull(comment);
        assertEquals(commentDTO.getComment(), comment.getComment());
    }

    @Test
    void updateComment() throws IllegalAccessException {
        // create comment
        when(commentService.update("1", commentDTO)).thenReturn(comment);
        // assert
        assertNotNull(comment);
        assertEquals(commentDTO.getComment(), comment.getComment());
    }

    @Test
    void getExistingCommentById() throws IllegalAccessException {
        Authentication context = SecurityContextHolder.getContext().getAuthentication();
        // create comment
        when(commentService.save("1",  context,  commentDTO)).thenReturn(comment);
        // get the expected comment
        Comment expectedComment = commentService.get(comment.getId());
        // assert
        assertNotNull(comment);
        assertNull(expectedComment);
        assertEquals(commentDTO.getComment(), comment.getComment());
    }

    @Test
    void deleteCommentById() throws IllegalAccessException {
        Authentication context = SecurityContextHolder.getContext().getAuthentication();
        // create comment
        when(commentService.save("1",  context,  commentDTO)).thenReturn(comment);
        // get the expected comment
        Comment expectedComment = commentService.get(comment.getId());
        // assert
        assertNotNull(comment);
        assertNull(expectedComment);
        assertEquals(commentDTO.getComment(), comment.getComment());
    }

    @Test
    void getAllComments() {
        Authentication context = SecurityContextHolder.getContext().getAuthentication();
        // create comment
        when(commentService.save("1",  context,  commentDTO)).thenReturn(comment);
        // get list of all comments
        comments = commentService.getAll();
        // assert to check comments
        assertEquals(0, comments.size());
    }


}