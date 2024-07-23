package com.eproject.Cinema.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_order_details")
public class OrderDetail extends BaseEntity {
      private int product_quantity;

      @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
      @JoinColumn(referencedColumnName = "id", name = "order_id", nullable = false)
      private Order order;

      @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
      @JoinColumn(referencedColumnName = "id", name = "product_id", nullable = false)
      private Product product;
}
