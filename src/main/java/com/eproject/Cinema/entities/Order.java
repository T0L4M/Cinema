package com.eproject.Cinema.entities;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_orders")
public class Order extends BaseEntity {
      private double amount;

      @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "order")
      @Transient
      @OnDelete(action = OnDeleteAction.CASCADE)
      private List<OrderDetail> orderDetails;
}
