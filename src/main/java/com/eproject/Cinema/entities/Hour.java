package com.eproject.Cinema.entities;

import java.sql.Time;

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
@Table(name = "tb_hours")
public class Hour extends BaseEntity {
      @Temporal(TemporalType.TIME)
      private Time time_from;

      @Temporal(TemporalType.TIME)
      private Time time_to;

      private double price;

      @OneToOne(mappedBy = "hour")
      private Showtime showtime;
}
