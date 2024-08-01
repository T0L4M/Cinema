package com.eproject.Cinema.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {

    @NotEmpty(message = "seatBooking is required!")
    private String seatBooking;

    private int quantity;

    private Long showtime_id;

    private Long customer_id;
}
