package com.eproject.Cinema.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_products")
public class Product extends BaseEntity {
      private String name;
      private String image;
      private String type;
      private double price;
      private boolean status;

      @OneToOne(mappedBy = "product")
      private OrderDetail orderDetail;
}
