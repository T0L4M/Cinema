package com.eproject.Cinema.dto;

import com.eproject.Cinema.entities.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetail {
      private Product product;
      private Long orderDetailId;
      private int quantity;
}
