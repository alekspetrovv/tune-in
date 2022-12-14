package com.example.blogservice.services;

import com.example.blogservice.models.Blog;
import com.example.blogservice.models.BlogDTO;
import com.example.blogservice.models.CommentDTO;
import com.example.blogservice.repositories.BlogRepository;
import com.example.blogservice.repositories.CommentDTORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final CommentDTORepository commentDTORepository;


    @Autowired
    public BlogService(BlogRepository blogRepository, CommentDTORepository commentDTORepository) {
        this.commentDTORepository = commentDTORepository;
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

    public Blog saveBlogComments(CommentDTO dto) {
        // set comment date
        Date date = new Date();
        dto.setCreatedDate(date);
        // save comment
        commentDTORepository.save(dto);
        // get found blog
        Blog foundBlog = getById(dto.getBlogId());
        // assign comments to blog
        List<CommentDTO> comments = commentDTORepository.findAll();
        foundBlog.setCommentList(comments);
        foundBlog.setComments(comments.size());
        // update blog
        blogRepository.save(foundBlog);
        return foundBlog;
    }

    public List<Blog> getAll() {
        return blogRepository.findAll();
    }

    public void deleteAll() {
        blogRepository.deleteAll();
    }


    public Blog getById(String id) {
        Optional<Blog> blog = blogRepository.findById(id);
        if (blog.isPresent()) {
            Blog foundBlog = blog.get();
            return foundBlog;
        }
        return null;
    }


    public void delete(String id) {
        Blog existingBlog = getById(id);
        blogRepository.delete(existingBlog);
    }


}
