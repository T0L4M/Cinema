package com.eproject.Cinema.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_bookings")
public class Booking extends BaseEntity {
      private String seatBooking;
      private int quantity;

      @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
      @JoinColumn(referencedColumnName = "id", name = "showtime_id", nullable = false)
      private Showtime showtime;

      @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
      @JoinColumn(referencedColumnName = "id", name = "customer_id", nullable = false)
      private Account customer;
}
