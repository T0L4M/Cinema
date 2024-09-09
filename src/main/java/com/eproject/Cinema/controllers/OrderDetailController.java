package com.eproject.Cinema.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eproject.Cinema.response.HttpResponse;
import com.eproject.Cinema.services.OrderDetailService;

@RestController
@RequestMapping("/order_details")
public class OrderDetailController extends BaseController {
      @Autowired
      OrderDetailService _detailService;

      @Autowired
      HttpResponse _httpResponse;

      @GetMapping()
      public ResponseEntity<?> getList() {
            return _httpResponse.success(_detailService.getAll());
      }
}
