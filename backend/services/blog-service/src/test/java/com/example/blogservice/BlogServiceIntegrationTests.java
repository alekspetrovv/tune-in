package com.example.blogservice;

import com.example.blogservice.controllers.BlogsController;
import com.example.blogservice.models.Blog;
import com.example.blogservice.models.BlogDTO;
import com.example.blogservice.services.BlogService;
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

import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BlogsController.class)
@AutoConfigureMockMvc
@ContextConfiguration
class BlogServiceIntegrationTests {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private BlogService blogService;
    private Blog blog;
    private BlogDTO blogDTO;
    private Blog blogOne;
    private Blog blogTwo;
    private Date date;

    @BeforeEach
    public void setup() {

        date = new Date();

        blogDTO = new BlogDTO();
        blogDTO.setBody("cool blog, I like this game");
        blogDTO.setTitle("Apex Legends");
        blogDTO.setComments(10);
        blogDTO.setCreatedDate(date);
        blogDTO.setUpdatedDate(date);

        blog = new Blog();
        blog.setId("1");
        blog.setBody("Patch notes 2.3v");
        blog.setTitle("Apex Legends");
        blog.setComments(10);
        blog.setCreatedDate(date);
        blog.setUpdatedDate(date);

        blogOne = new Blog();
        blogOne.setId("2");
        blogOne.setBody("Patch notes 2.2v");
        blogOne.setTitle("League of Legends");
        blogOne.setComments(60);
        blogOne.setCreatedDate(date);
        blogOne.setUpdatedDate(date);


        blogTwo = new Blog();
        blogTwo.setId("3");
        blogTwo.setBody("Patch notes 2.1v");
        blogTwo.setTitle("Rocket League");
        blogTwo.setComments(20);
        blogTwo.setCreatedDate(date);
        blogTwo.setUpdatedDate(date);

    }

    @Test
    @WithMockUser
    void create() throws Exception {
        mvc.perform(post("/blog/")
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"title\":\"Rocket League\",\"body\":\"Testing\",\"comments\":\"10\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andReturn();
    }


    @Test
    @WithMockUser
    void update() throws Exception {
        when(blogService.update(blog.getId(), blogDTO))
                .thenReturn(blog);
        mvc.perform(put("/blog/" + blog.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"title\":\"League of Legends\",\"body\":\"Updating\",\"comments\":\"50\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andReturn();
    }


    @Test
    @WithMockUser
    void getBlog() throws Exception {
        when(blogService
                .getById(blog.getId()))
                .thenReturn(blog);

        mvc.perform(get("/blog/" + blog.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.title").value("Apex Legends"))
                .andExpect(jsonPath("$.body").value("Patch notes 2.3v"))
                .andExpect(jsonPath("$.comments").value(10))
                .andReturn();
    }

    @Test
    @WithMockUser
    void getAll() throws Exception {
        when(blogService
                .getAll())
                .thenReturn(List.of(blog, blogOne, blogTwo));

        mvc.perform(get("/blog/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(3))
                .andReturn();
    }

    @Test
    @WithMockUser
    void deleteBlog() throws Exception {
        when(blogService
                .getById(blog.getId()))
                .thenReturn(blog);
        mvc.perform(delete("/blog/" + blog.getId())).andReturn();
    }


}
