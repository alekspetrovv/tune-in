package com.example.feedservice;

import com.example.feedservice.models.Comment;
import com.example.feedservice.models.CommentDTO;
import com.example.feedservice.repository.CommentRepository;
import com.example.feedservice.services.NewsFeedService;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class FeedServiceUnitTests {
    @MockBean
    private NewsFeedService newsFeedService;
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
        comment.setTitle("test");
        comment.setId("1");
        comment.setContent("cool blog, I like this game");
        comment.setCreatedDate(date);
        commentDTO = new CommentDTO();
        commentDTO.setTitle("test");
        commentDTO.setContent("cool blog, I like this game");
        commentDTO.setCreatedDate(date);
        commentOne = new Comment();
        commentOne.setId("2");
        commentOne.setContent("cool blog, I like this game, where can i buy it?");
        commentOne.setCreatedDate(date);
        commentTwo = new Comment();
        commentTwo.setId("3");
        commentTwo.setContent("cool blog, I like this game, where can i buy it, nice nice?");
        commentTwo.setCreatedDate(date);
    }

    @Test
    void createComment() {
        // create comment
        when(newsFeedService.createComment(commentDTO)).thenReturn(comment);
        // assert
        assertNotNull(comment);
        assertEquals(commentDTO.getTitle(), comment.getTitle());
        assertEquals(commentDTO.getContent(), comment.getContent());
    }

    @Test
    void updateComment() {
        // create comment
        when(newsFeedService.createComment(commentDTO)).thenReturn(comment);
        // assert
        assertNotNull(comment);
        assertEquals(commentDTO.getTitle(), comment.getTitle());
        assertEquals(commentDTO.getContent(), comment.getContent());
    }

    @Test
    void getExistingCommentById() {
        // create comment
        when(newsFeedService.createComment(commentDTO)).thenReturn(comment);
        // get the expected comment
        Comment expectedComment = newsFeedService.getCommentById(comment.getId());
        // assert
        assertNotNull(comment);
        assertNull(expectedComment);
        assertEquals(commentDTO.getTitle(), comment.getTitle());
        assertEquals(commentDTO.getContent(), comment.getContent());
    }

    @Test
    void deleteCommentById() {
        // create comment
        when(newsFeedService.createComment(commentDTO)).thenReturn(comment);
        // get the expected comment
        Comment expectedComment = newsFeedService.getCommentById(comment.getId());
        // assert
        assertNotNull(comment);
        assertNull(expectedComment);
        assertEquals(commentDTO.getTitle(), comment.getTitle());
        assertEquals(commentDTO.getContent(), comment.getContent());
    }

    @Test
    void getAllComments() {
        // create comment
        when(newsFeedService.createComment(commentDTO)).thenReturn(comment);
        // get list of all comments
        comments = newsFeedService.getComments();
        // assert to check comments
        assertEquals(0, comments.size());
    }


}