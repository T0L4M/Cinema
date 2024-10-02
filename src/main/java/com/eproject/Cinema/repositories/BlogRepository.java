package com.eproject.Cinema.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.eproject.Cinema.entities.Blog;

@Component
public interface BlogRepository extends JpaRepository<Blog, Long> {
      @Query(value = "SELECT * FROM tb_blogs WHERE status = true ORDER BY created_at DESC LIMIT 4 ", nativeQuery = true)
      public List<Blog> showNewBlogsWithStatus();

      // @Query(value = "SELECT * FROM tb_blogs WHERE status = true ORDER BY
      // created_at DESC LIMIT 8 ", nativeQuery = true)
      public List<Blog> findByStatus(boolean status);
}