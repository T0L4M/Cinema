package com.eproject.Cinema.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eproject.Cinema.response.HttpResponse;
import com.eproject.Cinema.services.BookingService;

@RestController
@RequestMapping("/bookings")
public class BookingController extends BaseController {
      @Autowired
      BookingService _bookingService;

      @Autowired
      HttpResponse _httpResponse;
}
