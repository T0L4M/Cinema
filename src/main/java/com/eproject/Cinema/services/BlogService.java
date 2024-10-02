package com.eproject.Cinema.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eproject.Cinema.entities.Blog;
import com.eproject.Cinema.repositories.BlogRepository;

@Service
public class BlogService {
      @Autowired
      BlogRepository _blogRepository;

      public List<Blog> getAll() {
            return _blogRepository.findAll();
      }

      public Blog create(Blog blog) {
            try {
                  return _blogRepository.save(blog);
            } catch (Exception e) {
                  e.printStackTrace();
            }

            return null;
      }

      public Blog detail(Long id) {
            return _blogRepository.findById(id).get();
      }

      public boolean delete(Long id) {
            try {
                  Blog blog = _blogRepository.findById(id).get();
                  if (blog != null) {
                        _blogRepository.delete(blog);
                        return true;
                  }
            } catch (Exception e) {
                  e.printStackTrace();
            }
            return false;
      }

      public List<Blog> showNewBlogs() {
            try {
                  return _blogRepository.showNewBlogsWithStatus();
            } catch (Exception e) {
                  e.printStackTrace();
            }
            return null;
      }

      public List<Blog> findByStatus() {
            return _blogRepository.findByStatus(true);
      }
}
