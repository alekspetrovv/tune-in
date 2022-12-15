package com.example.blogservice.repositories;

import com.example.blogservice.models.Blog;
import com.example.blogservice.models.CommentDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BlogRepository extends MongoRepository<Blog, String> {
}
