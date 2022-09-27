package com.example.blogservice.repositories;

import com.example.blogservice.modules.Blogs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlogsRepository extends JpaRepository<Blogs, Long> {
    Optional<Blogs> findById(Long id);
}
