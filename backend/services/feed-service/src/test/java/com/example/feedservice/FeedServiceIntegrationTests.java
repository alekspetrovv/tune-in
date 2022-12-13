package com.example.feedservice;

import com.example.feedservice.controllers.CommentController;
import com.example.feedservice.models.Comment;
import com.example.feedservice.models.CommentDTO;
import com.example.feedservice.services.CommentService;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CommentController.class)
@AutoConfigureMockMvc
@ContextConfiguration
class FeedServiceIntegrationTests {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private CommentService newsFeedService;
    private Comment comment;
    private CommentDTO commentDTO;
    private Comment commentOne;
    private Comment commentTwo;
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
    @WithMockUser
    void createComment() throws Exception {
      mvc.perform(post("/feed/comment/")
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"comment\":\"Cool blog man, interesting patch notes for Rocket League\"}")
                        .contentType(MediaType.APPLICATION_JSON))
              .andReturn();
    }

    @Test
    @WithMockUser
    void updateComment() throws Exception {
        when(newsFeedService.update(comment.getId(), commentDTO))
                .thenReturn(comment);
        mvc.perform(put("/feed/comment/" + comment.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"content\":\"Cool blog man, interesting patch notes for League\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }


    @Test
    @WithMockUser
    void getCommentById() throws Exception {
        when(newsFeedService
                .get(comment.getId()))
                .thenReturn(comment);

        mvc.perform(get("/feed/comment/" + comment.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.comment").value("cool blog, I like this game"))
                .andReturn();
    }

    @Test
    @WithMockUser
    void getAllComments() throws Exception {
        when(newsFeedService
                .getAll())
                .thenReturn(List.of(comment, commentOne, commentTwo));

        mvc.perform(get("/feed/comments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(3))
                .andReturn();
    }

    @Test
    @WithMockUser
    void deleteComment() throws Exception {
        when(newsFeedService
                .get(comment.getId()))
                .thenReturn(comment);

        mvc.perform(delete("/feed/comment/" + comment.getId()))
                .andReturn();
    }



}
