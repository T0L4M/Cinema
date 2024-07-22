package com.eproject.Cinema.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_payments")
public class Payment extends BaseEntity {
      private double amount;

      @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
      @JoinColumn(referencedColumnName = "id", name = "booking_id", nullable = false)
      private Booking booking;

      @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
      @JoinColumn(referencedColumnName = "id", name = "order_id", nullable = false)
      private Order order;
}
