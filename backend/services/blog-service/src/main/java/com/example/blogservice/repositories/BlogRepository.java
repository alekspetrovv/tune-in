package com.example.blogservice.repositories;

import com.example.blogservice.models.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BlogRepository extends MongoRepository<Blog, String> {
}
