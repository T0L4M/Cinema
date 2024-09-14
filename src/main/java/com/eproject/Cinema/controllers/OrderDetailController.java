package com.eproject.Cinema.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eproject.Cinema.entities.Auditoria;
import com.eproject.Cinema.entities.OrderDetail;
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

      @GetMapping("chart")
      public ResponseEntity<?> getProductChart() {
            return _httpResponse.success(_detailService.productChart());
      }

      @GetMapping("detail/{id}")
      public ResponseEntity<?> getOrderDetailByOrderId(@PathVariable Long id) {
            List<OrderDetail> orderDetails = _detailService.findByOrderId(id);
            if (orderDetails != null) {
                  return _httpResponse.success(orderDetails);
            }
            return _httpResponse.failure();
      }
}
