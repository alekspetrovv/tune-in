package com.example.blogservice.repositories;

import com.example.blogservice.models.Blog;
import com.example.blogservice.models.CommentDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentDTORepository extends MongoRepository<CommentDTO, String> {
}
