package com.eproject.Cinema.dto;

import java.util.List;
import java.util.Map;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutRequest {
      @NotEmpty(message = "Seat Booking List is required!")
      private List<String> seat_book;
      private Map<Long, Integer> productQuantities;
      private List<Long> productList;

      @Min(value = 0, message = "CustomerId is required!")
      private Long customer_id;
      private Long showtime_id;
}
