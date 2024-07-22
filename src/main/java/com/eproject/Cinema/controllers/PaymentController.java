package com.eproject.Cinema.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eproject.Cinema.response.HttpResponse;
import com.eproject.Cinema.services.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController extends BaseController {
      @Autowired
      PaymentService _paymentService;

      @Autowired
      HttpResponse _httpResponse;
}
