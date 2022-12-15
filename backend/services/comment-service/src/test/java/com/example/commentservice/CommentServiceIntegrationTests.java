package com.example.commentservice;

import com.example.commentservice.controllers.CommentController;
import com.example.commentservice.models.Comment;
import com.example.commentservice.models.CommentDTO;
import com.example.commentservice.services.CommentService;

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
class CommentServiceIntegrationTests {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private CommentService commentService;
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
      mvc.perform(post("/comment/submit")
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"comment\":\"Cool blog man, interesting patch notes for Rocket League\"}")
                        .contentType(MediaType.APPLICATION_JSON))
              .andReturn();
    }

    @Test
    @WithMockUser
    void updateComment() throws Exception {
        when(commentService.update(comment.getId(), commentDTO))
                .thenReturn(comment);
        mvc.perform(put("/comment/" + comment.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"content\":\"Cool blog man, interesting patch notes for League\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }


    @Test
    @WithMockUser
    void getCommentById() throws Exception {
        when(commentService
                .get(comment.getId()))
                .thenReturn(comment);

        mvc.perform(get("/comment/" + comment.getId()))
                .andDo(print())
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value("1"))
//                .andExpect(jsonPath("$.comment").value("cool blog, I like this game"))
                .andReturn();
    }

    @Test
    @WithMockUser
    void getAllComments() throws Exception {
        when(commentService
                .getAll())
                .thenReturn(List.of(comment, commentOne, commentTwo));

        mvc.perform(get("/comment/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(3))
                .andReturn();
    }

    @Test
    @WithMockUser
    void deleteComment() throws Exception {
        when(commentService
                .get(comment.getId()))
                .thenReturn(comment);

        mvc.perform(delete("/comment/" + comment.getId()))
                .andReturn();
    }



}
