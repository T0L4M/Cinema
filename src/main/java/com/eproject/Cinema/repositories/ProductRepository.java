package com.eproject.Cinema.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.eproject.Cinema.entities.Product;

@Component
public interface ProductRepository extends JpaRepository<Product, Long> {
      public List<Product> findByStatus(boolean stt);

      public List<Product> findByType(String type);
}
