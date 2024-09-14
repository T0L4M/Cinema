package com.eproject.Cinema.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eproject.Cinema.dto.PaymentDTO;
import com.eproject.Cinema.entities.Booking;
import com.eproject.Cinema.entities.Order;
import com.eproject.Cinema.entities.Payment;
import com.eproject.Cinema.entities.Showtime;
import com.eproject.Cinema.response.HttpResponse;
import com.eproject.Cinema.services.BookingService;
import com.eproject.Cinema.services.OrderService;
import com.eproject.Cinema.services.PaymentService;
import com.eproject.Cinema.services.ShowtimeService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/payments")
public class PaymentController extends BaseController {
      @Autowired
      PaymentService _paymentService;

      @Autowired
      ShowtimeService _showtimeService;

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

                  Booking booking = _bookingService.detail(Long.parseLong(paymentDTO.getBookingId()));

                  if (booking != null) {
                        Order order = !paymentDTO.getOrderId().isEmpty()
                                    ? _orderService.detail(Long.parseLong(paymentDTO.getOrderId()))
                                    : null;
                        System.out.println("ORRDERRRRR: " + order);
                        Payment payment = new Payment(paymentDTO.getAmount(), booking, order);
                        Payment savedPayment = _paymentService.create(payment);

                        if (savedPayment != null) {
                              Showtime show = booking.getShowtime();

                              QRCodeWriter barcodeWriter = new QRCodeWriter();

                              BitMatrix bitMatrix = barcodeWriter.encode(
                                          "http://localhost:3000/admin/payment/detail/" + savedPayment.getId(),
                                          BarcodeFormat.QR_CODE, 200, 200);

                              boolean rs = _paymentService.sendQrCodetoMail(booking.getCustomer().getEmail(),
                                          MatrixToImageWriter.toBufferedImage(bitMatrix), booking.getSeatBooking(),
                                          show,
                                          savedPayment.getAmount());
                              if (rs) {
                                    return _httpResponse.success();
                              }
                              return _httpResponse.failure();
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

      @GetMapping("chart")
      public ResponseEntity<?> getPaymentChart() {
            return _httpResponse.success(_paymentService.paymentChart());
      }

      @GetMapping("detail/{id}")
      public ResponseEntity<?> detail(@PathVariable Long id) {
            Payment payment = _paymentService.detail(id);
            if (payment != null) {
                  return _httpResponse.success(payment);
            }
            return _httpResponse.failure();
      }

      @DeleteMapping("delete/{id}")
      public ResponseEntity<?> delPayment(@PathVariable Long id) {
            boolean status = _paymentService.delete(id);
            if (status) {
                  return _httpResponse.success();
            }
            return _httpResponse.failure();
      }

}
