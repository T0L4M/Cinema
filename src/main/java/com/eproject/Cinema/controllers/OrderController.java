package com.eproject.Cinema.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eproject.Cinema.dto.CheckoutRequest;
import com.eproject.Cinema.dto.HourDTO;
import com.eproject.Cinema.dto.ProductDetail;
import com.eproject.Cinema.entities.Hour;
import com.eproject.Cinema.entities.Order;
import com.eproject.Cinema.entities.OrderDetail;
import com.eproject.Cinema.entities.Product;
import com.eproject.Cinema.entities.Showtime;
import com.eproject.Cinema.response.CheckoutResponse;
import com.eproject.Cinema.response.HttpResponse;
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
      HttpResponse _httpResponse;

      // @PostMapping()
      // public ResponseEntity<?> create(@Valid @RequestBody CheckoutRequest request,
      // BindingResult br) {
      // try {
      // if (br.hasErrors()) {
      // return _httpResponse.unprocessable(getErrors(br));
      // }
      // List<ProductDetail> bimbims = new ArrayList<>();
      // if (!request.getProductList().isEmpty()) {
      // List<Product> snacks = new ArrayList<>();
      // for (Long productId : request.getProductList()) {
      // Product snack = _productService.detail(productId);
      // snacks.add(snack);
      // }
      // Order order = new Order();
      // _orderService.create(order);

      // for (Map.Entry<Long, Integer> entry :
      // request.getProductQuantities().entrySet()) {
      // Product snack = snacks.stream().filter(p ->
      // p.getId().equals(entry.getKey())).findFirst()
      // .orElse(null);
      // if (snack != null) {
      // OrderDetail orderDetail = new OrderDetail();
      // orderDetail.setOrder(order);
      // orderDetail.setProduct(snack);
      // orderDetail.setProduct_quantity(entry.getValue());
      // _orderDetailService.create(orderDetail);
      // }
      // }

      // for (Product snack : snacks) {
      // List<ProductDetail> details = _orderDetailService
      // .findByOrderIdAndProductId(order.getId(), snack.getId())
      // .stream()
      // .map(od -> new ProductDetail(snack, od.getId(),
      // od.getProduct_quantity()))
      // .collect(Collectors.toList());
      // if (!details.isEmpty()) {
      // bimbims.addAll(details);
      // }
      // }

      // }

      // Showtime showtime =
      // _showtimeService.findShowtimeByMovieTitleAndSuatAndShowtimeDate(
      // request.getBookTitle(), request.getBookSuat(), request.getBookShowdate());

      // int seatTotal = request.getSeatCheck().size();
      // String seatCheck = String.join(" ", request.getSeatCheck());
      // // String convertSeat = // Call NhanvienController's convertSeats method
      // // (implement logic here)

      // double amount = showtime.getPrice() * seatTotal;

      // CheckoutResponse checkoutResponse = new CheckoutResponse(
      // showtime, seatTotal, seatCheck, amount, bimbims);

      // return ResponseEntity.ok(checkoutResponse);
      // } catch (Exception e) {
      // return _httpResponse.failure();
      // }
      // }

}
