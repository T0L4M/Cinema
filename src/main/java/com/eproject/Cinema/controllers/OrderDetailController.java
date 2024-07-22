package com.eproject.Cinema.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eproject.Cinema.response.HttpResponse;
import com.eproject.Cinema.services.OrderDetailService;

@RestController
@RequestMapping("/details")
public class OrderDetailController extends BaseController {
      @Autowired
      OrderDetailService _detailService;

      @Autowired
      HttpResponse _httpResponse;
}
