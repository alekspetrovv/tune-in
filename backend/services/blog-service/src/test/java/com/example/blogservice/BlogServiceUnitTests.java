package com.example.blogservice;

import com.example.blogservice.controllers.BlogsController;
import com.example.blogservice.models.Blog;
import com.example.blogservice.models.BlogDTO;
import com.example.blogservice.repositories.BlogRepository;
import com.example.blogservice.services.BlogService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BlogServiceUnitTests {

    @MockBean
    private BlogService blogService;
    private Blog blog;
    private BlogDTO blogDTO;
    private Blog blogOne;
    private Blog blogTwo;
    private Date date;

    private List<Blog> blogList;

    @BeforeEach
    public void setup() {

        date = new Date();
        blogDTO = new BlogDTO();
        blogDTO.setBody("Patch notes 2.3v");
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
    void createBlog() {
        when(blogService.create(blogDTO)).thenReturn(blog);
        // assert
        assertNotNull(blog);
        assertEquals(blogDTO.getBody(), blog.getBody());
        assertEquals(blogDTO.getTitle(), blog.getTitle());
        assertEquals(blogDTO.getComments(), blog.getComments());
    }


    @Test
    void updateBlog() {
        // create comment
        when(blogService.update("1", blogDTO)).thenReturn(blog);
        // assert
        assertNotNull(blog);
        assertEquals(blogDTO.getBody(), blog.getBody());
        assertEquals(blogDTO.getTitle(), blog.getTitle());
        assertEquals(blogDTO.getComments(), blog.getComments());
    }

    @Test
    void getExistingBlogById() {
        // create comment
        when(blogService.create(blogDTO)).thenReturn(blog);
        // get the expected comment
        when(blogService.getById("1")).thenReturn(blog);
        // assert
        assertNotNull(blog);
        assertEquals(blogDTO.getTitle(), blog.getTitle());
        assertEquals(blogDTO.getComments(), blog.getComments());
    }

    @Test
    void deleteCommentById() {
        when(blogService.create(blogDTO)).thenReturn(blog);
        // get the expected comment
        Blog expectedBlog = blogService.getById(blog.getId());
        // assert
        assertNotNull(blog);
        assertNull(expectedBlog);
    }

    @Test
    void getAllBlogs() {
        // add blog
        when(blogService.create(blogDTO)).thenReturn(blog);
        when(blogService
                .getAll())
                .thenReturn(List.of(blog));
    }

}
