package com.eproject.Cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.eproject.Cinema.entities.Product;

@Component
public interface ProductRepository extends JpaRepository<Product, Long> {

}
