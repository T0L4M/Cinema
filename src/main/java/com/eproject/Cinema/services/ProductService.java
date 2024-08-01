package com.eproject.Cinema.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eproject.Cinema.entities.Product;
import com.eproject.Cinema.repositories.ProductRepository;

@Service
public class ProductService {
      @Autowired
      ProductRepository _productRepository;

      public List<Product> getAll() {
            return _productRepository.findAll();
      }

      public Product create(Product product) {
            try {
                  return _productRepository.save(product);
            } catch (Exception e) {
                  e.printStackTrace();
            }
            return null;
      }

      public Product detail(Long id) {
            return _productRepository.findById(id).get();
      }

      public Product update(Product product) {
            try {
                  return _productRepository.save(product);
            } catch (Exception e) {
                  e.printStackTrace();
            }
            return null;
      }

      public boolean delete(Long id) {
            try {
                  Product product = _productRepository.findById(id).get();
                  if (product != null) {
                        _productRepository.delete(product);
                        return true;
                  }
            } catch (Exception e) {
                  e.printStackTrace();
            }
            return false;
      }
}
