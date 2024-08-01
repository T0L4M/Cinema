package com.eproject.Cinema.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eproject.Cinema.dto.ProductDTO;
import com.eproject.Cinema.entities.Product;
import com.eproject.Cinema.response.HttpResponse;
import com.eproject.Cinema.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController extends BaseController {
      @Value("${file.upload-dir}")
      private String uploadDir;

      @Autowired
      ProductService _productService;

      @Autowired
      HttpResponse _httpResponse;

      @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
      public ResponseEntity<?> create(@Valid ProductDTO productDTO, @RequestParam("image") MultipartFile file,
                  BindingResult br) {
            try {
                  if (br.hasErrors()) {
                        return _httpResponse.unprocessable(getErrors(br));
                  }
                  Product product = new Product();
                  if (!file.isEmpty()) {
                        productDTO.setImage(file);
                        Path path = Paths.get(uploadDir + "/products");
                        if (!Files.exists(path)) {
                              Files.createDirectories(path);
                        }
                        String fileName = new Timestamp(System.currentTimeMillis()).getTime()
                                    + productDTO.getImage().getOriginalFilename();
                        Path filePath = path.resolve(fileName);
                        Files.copy(productDTO.getImage().getInputStream(), filePath);
                        BeanUtils.copyProperties(productDTO, product);
                        product.setImage(fileName);
                        Product rs = _productService.create(product);

                        if (rs != null) {
                              return _httpResponse.success(rs);
                        }
                  }
                  return _httpResponse.failure();
            } catch (Exception e) {
                  return _httpResponse.failure();
            }
      }

      @GetMapping()
      public ResponseEntity<?> getList() {
            return _httpResponse.success(_productService.getAll());
      }

      @PutMapping(path = "edit/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
      public ResponseEntity<?> update(@PathVariable Long id, @Valid ProductDTO productDTO,
                  @RequestParam(name = "image", required = false) MultipartFile file, BindingResult br) {

            if (br.hasErrors()) {
                  return _httpResponse.unprocessable(getErrors(br));
            }
            Product product = _productService.detail(id);
            if (product != null) {
                  if (!file.isEmpty()) {
                        try {
                              productDTO.setImage(file);
                              Path path = Paths.get(uploadDir, "products");
                              Path filepath = Paths.get(uploadDir, "products", product.getImage());
                              Files.delete(filepath);
                              String fileName = new Timestamp(System.currentTimeMillis()).getTime()
                                          + productDTO.getImage().getOriginalFilename();

                              Path filePath = path.resolve(fileName);
                              Files.copy(productDTO.getImage().getInputStream(), filePath);
                              product.setImage(fileName);
                        } catch (Exception e) {
                              return _httpResponse.failure();
                        }
                  }
                  BeanUtils.copyProperties(productDTO, product);
                  Product rs = _productService.create(product);
                  if (rs != null) {
                        return _httpResponse.success(rs);
                  }
            }
            return _httpResponse.failure();

      }

      @DeleteMapping("delete/{id}")
      public ResponseEntity<?> delete(@PathVariable Long id) {
            Product rs = _productService.detail(id);
            if (rs != null) {
                  try {
                        Path filepath = Paths.get(uploadDir, "products", rs.getImage());
                        Files.delete(filepath);
                        boolean result = _productService.delete(id);
                        if (result) {
                              return _httpResponse.success(result);
                        }
                        return _httpResponse.failure();
                  } catch (Exception e) {
                        return _httpResponse.failure();
                  }
            }
            return _httpResponse.failure();
      }

}
