package com.example.blogservice.services;

import com.example.blogservice.models.Blog;
import com.example.blogservice.models.BlogDTO;
import com.example.blogservice.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    private BlogRepository blogRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Blog create(BlogDTO dto) {
        Date date = new Date();
        Blog blog = Blog.builder()
                .title(dto.getTitle())
                .body(dto.getBody())
                .createdDate(date)
                .updatedDate(date)
                .comments(dto.getComments())
                .build();
        blogRepository.save(blog);
        return blog;
    }

    public Blog update(String id, BlogDTO dto) {
        Blog updatedBlog = getById(id);
        Date date = new Date();
        updatedBlog.setTitle(dto.getTitle());
        updatedBlog.setBody(dto.getBody());
        updatedBlog.setComments(dto.getComments());
        updatedBlog.setUpdatedDate(date);
        blogRepository.save(updatedBlog);
        return updatedBlog;
    }

    public List<Blog> getAll() {
        return blogRepository.findAll();
    }

    public Blog getById(String id) {
        Optional<Blog> blog = blogRepository.findById(id);
        Blog existingBlog = blog.stream().findFirst().orElse(null);
        return existingBlog;
    }


    public void delete(String id) {
        Blog existingBlog = getById(id);
        blogRepository.delete(existingBlog);
    }


}
