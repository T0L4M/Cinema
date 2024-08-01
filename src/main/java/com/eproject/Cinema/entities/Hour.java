package com.eproject.Cinema.entities;

import java.sql.Time;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
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
@Table(name = "tb_hours")
public class Hour extends BaseEntity {
      @Temporal(TemporalType.TIME)
      private Time time_from;

      @Temporal(TemporalType.TIME)
      private Time time_to;

      private double price;

      @OneToMany(mappedBy = "hour", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
      @Transient
      @JsonIgnore
      @OnDelete(action = OnDeleteAction.CASCADE)
      private List<Showtime> showtimes;
}
