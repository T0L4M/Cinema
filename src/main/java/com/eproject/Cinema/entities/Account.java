package com.eproject.Cinema.entities;

import java.sql.Date;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
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

      @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
      @Transient
      @OnDelete(action = OnDeleteAction.CASCADE)
      private List<Booking> bookings;
}
