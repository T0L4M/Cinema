package com.eproject.Cinema.controllers;

import java.util.ArrayList;
import java.util.List;

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

import com.eproject.Cinema.dto.BookingDTO;
import com.eproject.Cinema.dto.ErrorDTO;
import com.eproject.Cinema.dto.ShowtimeDTO;
import com.eproject.Cinema.entities.Account;
import com.eproject.Cinema.entities.Auditoria;
import com.eproject.Cinema.entities.Booking;
import com.eproject.Cinema.entities.Hour;
import com.eproject.Cinema.entities.Movie;
import com.eproject.Cinema.entities.Showtime;
import com.eproject.Cinema.response.HttpResponse;
import com.eproject.Cinema.services.AccountService;
import com.eproject.Cinema.services.BookingService;
import com.eproject.Cinema.services.ShowtimeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bookings")
public class BookingController extends BaseController {
      @Autowired
      BookingService _bookingService;

      @Autowired
      ShowtimeService _showtimeService;

      @Autowired
      AccountService _accountService;

      @Autowired
      HttpResponse _httpResponse;

      @PostMapping()
      public ResponseEntity<?> create(@Valid @RequestBody BookingDTO bookingDTO, BindingResult br) {
            try {
                  if (br.hasErrors()) {
                        return _httpResponse.unprocessable(getErrors(br));
                  }
                  Account account = _accountService.detail(bookingDTO.getCustomer_id());
                  Showtime showtime = _showtimeService.detail(bookingDTO.getShowtime_id());
                  if (account != null && showtime != null) {
                        Booking booking = new Booking();
                        booking.setSeatBooking(bookingDTO.getSeatBooking());
                        booking.setQuantity(bookingDTO.getQuantity());
                        booking.setCustomer(account);
                        booking.setShowtime(showtime);
                        Booking rs = _bookingService.create(booking);
                        if (rs != null) {
                              return _httpResponse.success(rs);
                        }
                        return _httpResponse.failure();
                  }
                  List<ErrorDTO> errors = new ArrayList();
                  errors.add(new ErrorDTO("code", "Invalid category"));
                  return _httpResponse.unprocessable(errors);
            } catch (Exception e) {
                  return _httpResponse.failure();
            }
      }

      @GetMapping()
      public ResponseEntity<?> getList() {
            return _httpResponse.success(_bookingService.getAll());
      }

      @GetMapping("detail/{id}")
      public ResponseEntity<?> detail(@PathVariable Long id) {
            Booking book = _bookingService.detail(id);
            if (book != null) {
                  return _httpResponse.success(book);
            }
            return _httpResponse.failure();
      }

      @PutMapping("edit/{id}")
      public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody BookingDTO bookingDTO,
                  BindingResult br) {
            try {
                  if (br.hasErrors()) {
                        return _httpResponse.unprocessable(getErrors(br));
                  }
                  Account account = _accountService.detail(bookingDTO.getCustomer_id());
                  Showtime showtime = _showtimeService.detail(bookingDTO.getShowtime_id());
                  Booking booking = _bookingService.detail(id);
                  if (booking != null) {
                        booking.setSeatBooking(bookingDTO.getSeatBooking());
                        booking.setQuantity(bookingDTO.getQuantity());
                        booking.setCustomer(account);
                        booking.setShowtime(showtime);
                        Booking rs = _bookingService.create(booking);
                        if (rs != null) {
                              return _httpResponse.success(rs);
                        }
                  }
            } catch (Exception e) {
                  return _httpResponse.failure();
            }
            return _httpResponse.failure();
      }

      @DeleteMapping("delete/{id}")
      public ResponseEntity<?> delBook(@PathVariable Long id) {
            boolean status = _bookingService.delete(id);
            if (status) {
                  return _httpResponse.success();
            }
            return _httpResponse.failure();
      }
}
