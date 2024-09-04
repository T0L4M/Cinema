package com.eproject.Cinema.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eproject.Cinema.dto.AuditoriaDTO;
import com.eproject.Cinema.dto.PaymentDTO;
import com.eproject.Cinema.entities.Auditoria;
import com.eproject.Cinema.entities.Booking;
import com.eproject.Cinema.entities.Order;
import com.eproject.Cinema.entities.Payment;
import com.eproject.Cinema.response.HttpResponse;
import com.eproject.Cinema.services.BookingService;
import com.eproject.Cinema.services.OrderService;
import com.eproject.Cinema.services.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/payments")
public class PaymentController extends BaseController {
      @Autowired
      PaymentService _paymentService;

      @Autowired
      OrderService _orderService;

      @Autowired
      BookingService _bookingService;

      @Autowired
      HttpResponse _httpResponse;

      @PostMapping()
      public ResponseEntity<?> create(@Valid @RequestBody PaymentDTO paymentDTO, BindingResult br) {
            try {
                  if (br.hasErrors()) {
                        return _httpResponse.unprocessable(getErrors(br));
                  }
                  Payment rs;
                  Booking booking = _bookingService.detail(Long.parseLong(paymentDTO.getBookingId()));
                  if (booking != null) {
                        if (!paymentDTO.getOrderId().isEmpty()) {
                              Order order = _orderService.detail(Long.parseLong(paymentDTO.getOrderId()));
                              rs = _paymentService.create(new Payment(paymentDTO.getAmount(), booking, order));
                        } else {
                              rs = _paymentService.create(new Payment(paymentDTO.getAmount(), booking, null));
                        }
                        if (rs != null) {
                              return _httpResponse.success(rs);
                        }
                  }
                  return _httpResponse.failure();
            } catch (Exception e) {
                  return _httpResponse.failure("HONG");
            }
      }

      @GetMapping()
      public ResponseEntity<?> getList() {
            return _httpResponse.success(_paymentService.getAll());
      }

      @GetMapping("detail/{id}")
      public ResponseEntity<?> detail(@PathVariable Long id) {
            Payment payment = _paymentService.detail(id);
            if (payment != null) {
                  return _httpResponse.success(payment);
            }
            return _httpResponse.failure();
      }

      // @PutMapping("edit/{id}")
      // public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody
      // PaymentDTO payment, BindingResult br) {
      // try {
      // if (br.hasErrors()) {
      // return _httpResponse.unprocessable(getErrors(br));
      // }
      // Auditoria auditoria = _auditoriaService.detail(id);
      // if (auditoria != null) {
      // BeanUtils.copyProperties(audi, auditoria);
      // Auditoria rs = _auditoriaService.update(auditoria);
      // if (rs != null) {
      // return _httpResponse.success(rs);
      // }
      // }
      // } catch (Exception e) {
      // return _httpResponse.failure();
      // }
      // return _httpResponse.failure();
      // }

      @DeleteMapping("delete/{id}")
      public ResponseEntity<?> delPayment(@PathVariable Long id) {
            boolean status = _paymentService.delete(id);
            if (status) {
                  return _httpResponse.success();
            }
            return _httpResponse.failure();
      }
}
