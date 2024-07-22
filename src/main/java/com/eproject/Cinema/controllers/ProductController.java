package com.eproject.Cinema.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eproject.Cinema.response.HttpResponse;
import com.eproject.Cinema.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController extends BaseController {
      @Autowired
      ProductService _productService;

      @Autowired
      HttpResponse _httpResponse;

}
