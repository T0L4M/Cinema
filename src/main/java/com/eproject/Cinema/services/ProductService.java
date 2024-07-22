package com.eproject.Cinema.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eproject.Cinema.repositories.ProductRepository;

@Service
public class ProductService {
      @Autowired
      ProductRepository _productRepository;
}
