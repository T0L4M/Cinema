package com.eproject.Cinema.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
      private double amount;
      @NotEmpty(message = "BookingID is required!")
      private String bookingId;
      private String orderId;
}
