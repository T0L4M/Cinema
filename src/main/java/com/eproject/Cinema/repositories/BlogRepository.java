package com.eproject.Cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.eproject.Cinema.entities.Blog;

@Component
public interface BlogRepository extends JpaRepository<Blog, Long> {
}
