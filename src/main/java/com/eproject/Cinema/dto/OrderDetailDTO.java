package com.eproject.Cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderDetailDTO {
    private int product_quantity;

    private Long order_id;

    private Long product_id;
}
