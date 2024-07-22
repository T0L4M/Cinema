package com.eproject.Cinema.entities;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_accounts")
public class Account extends BaseEntity {
      private String name;
      private String password;
      private String email;
      private boolean gender;
      private String phone;

      @Temporal(TemporalType.DATE)
      private Date dob;

      private String address;
      private String role;

      @OneToOne(mappedBy = "customer")
      private Booking booking;
}
