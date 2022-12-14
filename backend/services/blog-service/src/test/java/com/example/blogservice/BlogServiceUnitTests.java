package com.example.blogservice;

import com.example.blogservice.models.Blog;
import com.example.blogservice.models.BlogDTO;
import com.example.blogservice.services.BlogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @BeforeEach
    public void setup() {

        Date date = new Date();
        blogDTO = new BlogDTO();
        blogDTO.setBody("Patch notes 2.3v");
        blogDTO.setTitle("Apex Legends");
        blogDTO.setCreatedDate(date);
        blogDTO.setUpdatedDate(date);

        blog = new Blog();
        blog.setId("1");
        blog.setBody("Patch notes 2.3v");
        blog.setTitle("Apex Legends");
        blog.setComments(10);
        blog.setCreatedDate(date);
        blog.setUpdatedDate(date);

        Blog blogOne = new Blog();
        blogOne.setId("2");
        blogOne.setBody("Patch notes 2.2v");
        blogOne.setTitle("League of Legends");
        blogOne.setComments(60);
        blogOne.setCreatedDate(date);
        blogOne.setUpdatedDate(date);


        Blog blogTwo = new Blog();
        blogTwo.setId("3");
        blogTwo.setBody("Patch notes 2.1v");
        blogTwo.setTitle("Rocket League");
        blogTwo.setComments(20);
        blogTwo.setCreatedDate(date);
        blogTwo.setUpdatedDate(date);

    }


    @Test
    void createBlog() {
        Authentication context = SecurityContextHolder.getContext().getAuthentication();

        when(blogService.create(context,blogDTO)).thenReturn(blog);
        // assert
        assertNotNull(blog);
        assertEquals(blogDTO.getBody(), blog.getBody());
        assertEquals(blogDTO.getTitle(), blog.getTitle());
    }


    @Test
    void updateBlog() throws IllegalAccessException {
        // create comment
        when(blogService.update("1", blogDTO)).thenReturn(blog);
        // assert
        assertNotNull(blog);
        assertEquals(blogDTO.getBody(), blog.getBody());
        assertEquals(blogDTO.getTitle(), blog.getTitle());
    }

    @Test
    void getExistingBlogById() throws IllegalAccessException {
        Authentication context = SecurityContextHolder.getContext().getAuthentication();

        // create comment
        when(blogService.create(context,blogDTO)).thenReturn(blog);
        // get the expected comment
        when(blogService.getById("1")).thenReturn(blog);
        // assert
        assertNotNull(blog);
        assertEquals(blogDTO.getTitle(), blog.getTitle());
    }

    @Test
    void deleteCommentById() throws IllegalAccessException {
        Authentication context = SecurityContextHolder.getContext().getAuthentication();

        when(blogService.create(context,blogDTO)).thenReturn(blog);
        // get the expected comment
        Blog expectedBlog = blogService.getById(blog.getId());
        // assert
        assertNotNull(blog);
        assertNull(expectedBlog);
    }

    @Test
    void getAllBlogs() {
        Authentication context = SecurityContextHolder.getContext().getAuthentication();
        // add blog
        when(blogService.create(context,blogDTO)).thenReturn(blog);
        when(blogService
                .getAll())
                .thenReturn(List.of(blog));
    }

}
