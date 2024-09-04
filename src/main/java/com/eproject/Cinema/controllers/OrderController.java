package com.eproject.Cinema.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eproject.Cinema.dto.CheckoutRequest;
import com.eproject.Cinema.entities.Booking;
import com.eproject.Cinema.entities.Order;
import com.eproject.Cinema.entities.OrderDetail;
import com.eproject.Cinema.entities.Product;
import com.eproject.Cinema.entities.Showtime;
import com.eproject.Cinema.entities.User;
import com.eproject.Cinema.response.HttpResponse;
import com.eproject.Cinema.services.AccountService;
import com.eproject.Cinema.services.BookingService;
import com.eproject.Cinema.services.OrderDetailService;
import com.eproject.Cinema.services.OrderService;
import com.eproject.Cinema.services.ProductService;
import com.eproject.Cinema.services.ShowtimeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController extends BaseController {
      @Autowired
      OrderService _orderService;
      @Autowired
      OrderDetailService _orderDetailService;

      @Autowired
      ProductService _productService;

      @Autowired
      ShowtimeService _showtimeService;

      @Autowired
      BookingService _bookingService;

      @Autowired
      AccountService _accountService;

      @Autowired
      HttpResponse _httpResponse;

      @PostMapping("/allCreate")
      public ResponseEntity<?> create(@Valid @RequestBody CheckoutRequest request,
                  BindingResult br) {
            try {
                  if (br.hasErrors()) {
                        return _httpResponse.unprocessable(getErrors(br));
                  }
                  List<OrderDetail> bimbims = new ArrayList<>();
                  Order order = new Order();
                  if (!request.getProductList().isEmpty()) {
                        _orderService.create(order);
                        List<Product> snacks = new ArrayList<>();
                        for (Long productId : request.getProductList()) {
                              Product snack = _productService.detail(productId);
                              snacks.add(snack);
                        }
                        for (Map.Entry<Long, Integer> entry : request.getProductQuantities().entrySet()) {
                              Product snack = snacks.stream().filter(p -> p.getId().equals(entry.getKey())).findFirst()
                                          .orElse(null);
                              if (snack != null) {
                                    OrderDetail orderDetail = new OrderDetail();
                                    orderDetail.setOrder(order);
                                    orderDetail.setProduct(snack);
                                    orderDetail.setProduct_quantity(entry.getValue());
                                    _orderDetailService.create(orderDetail);
                              }
                        }
                        double productAmount = 0;
                        for (Product snack : snacks) {
                              OrderDetail od = _orderDetailService.findByOrderIdAndProductId(order.getId(),
                                          snack.getId());
                              productAmount += (od.getProduct_quantity() * od.getProduct().getPrice());
                              bimbims.add(od);
                        }
                        order.setOrderDetails(bimbims);
                        order.setAmount(productAmount);
                        _orderService.update(order);

                  }
                  Showtime showtime = _showtimeService.detail(request.getShowtimeId());
                  if (showtime == null) {
                        return _httpResponse.failure("INVALID SHOWTIME ID");
                  }
                  int seatTotal = request.getSeatBookingList().size();
                  String seatCheck = String.join(" ", request.getSeatBookingList());
                  double seatAmount = showtime.getHour().getPrice() * seatTotal;
                  User customer = _accountService.detail(request.getCustomerId());
                  Booking book = new Booking(seatAmount, seatCheck, seatTotal, showtime, customer);
                  Booking rs = _bookingService.create(book);
                  if (rs != null) {
                        if (!request.getProductList().isEmpty()) {
                              return _httpResponse.success(order);
                        }
                        return _httpResponse.success(rs);
                  }

            } catch (Exception e) {
                  return _httpResponse.failure();
            }
            return _httpResponse.failure();

      }

}
