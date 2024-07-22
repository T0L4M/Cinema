package com.eproject.Cinema.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eproject.Cinema.repositories.OrderDetailRepository;

@Service
public class OrderDetailService {
      @Autowired
      OrderDetailRepository _orderDetailRepository;
}
