package com.example.blogservice.services;

import com.example.blogservice.models.Blog;
import com.example.blogservice.models.BlogDTO;
import com.example.blogservice.models.CommentDTO;
import com.example.blogservice.repositories.BlogRepository;
import com.example.blogservice.repositories.CommentDTORepository;
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
public class BlogService {

    private final BlogRepository blogRepository;
    private final CommentDTORepository commentDTORepository;


    @Autowired
    public BlogService(BlogRepository blogRepository, CommentDTORepository commentDTORepository) {
        this.commentDTORepository = commentDTORepository;
        this.blogRepository = blogRepository;
    }


    public Blog create(Authentication context, BlogDTO dto) {
        // get user id
        String userId = context.getName();
        // create blog
        Date date = new Date();
        Blog blog = Blog.builder().title(dto.getTitle()).body(dto.getBody()).updatedDate(date).createdDate(date).userId(userId).build();
        // send comment to the queue
        blogRepository.save(blog);
        return blog;
    }

    public Blog update(String id, BlogDTO blogDTO) throws IllegalAccessException {
        Date date = new Date();
        Blog updatedBlog = getById(id);
        updatedBlog.setTitle(blogDTO.getTitle());
        updatedBlog.setBody(blogDTO.getBody());
        updatedBlog.setUpdatedDate(date);
        blogRepository.save(updatedBlog);
        return updatedBlog;
    }


    public List<Blog> getAll() {
        return blogRepository.findAll();
    }

    public void deleteAll() {
        blogRepository.deleteAll();
    }


    public Blog getById(String id) throws IllegalAccessException {
        Optional<Blog> blog = blogRepository.findById(id);
        if (blog.isPresent()) {
            Blog foundBlog = blog.get();
            return foundBlog;
        }
        throw new IllegalAccessException("Blog not found");
    }


    public void delete(String id) throws IllegalAccessException {
        Blog existingBlog = getById(id);
        blogRepository.delete(existingBlog);
    }


    public ResponseEntity<?> saveBlog(BlogDTO blogDTO) {
        // get logged in user
        Authentication context = SecurityContextHolder.getContext().getAuthentication();
        try {
            // post comment for blog
            Blog blog = create(context, blogDTO);
            return new ResponseEntity<>(blog, HttpStatus.OK);
        } catch (Exception e) {
            // throw exception
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity<?> updateBlog(String id, BlogDTO blogDTO) {
        try {
            // post comment for blog
            Blog blog = update(id, blogDTO);
            return new ResponseEntity<>(blog, HttpStatus.OK);
        } catch (Exception e) {
            // throw exception
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public Blog saveBlogComments(CommentDTO dto) throws IllegalAccessException {
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
}
