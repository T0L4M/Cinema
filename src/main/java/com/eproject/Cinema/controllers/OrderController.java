package com.eproject.Cinema.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eproject.Cinema.response.HttpResponse;
import com.eproject.Cinema.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController extends BaseController {
      @Autowired
      OrderService _orderService;

      @Autowired
      HttpResponse _httpResponse;
}