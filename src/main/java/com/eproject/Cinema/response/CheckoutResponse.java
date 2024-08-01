package com.eproject.Cinema.response;

import java.util.List;

import com.eproject.Cinema.dto.ProductDetail;
import com.eproject.Cinema.entities.Showtime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutResponse {
      private Showtime showtime;
      private int seatTotal;
      private String seatCheck;
      private double amount;
      private List<ProductDetail> productDetails;

}
