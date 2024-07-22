package com.eproject.Cinema.entities;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
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
@Table(name = "tb_showtimes")
public class Showtime extends BaseEntity {
      private boolean status;

      @Temporal(TemporalType.DATE)
      private Date showtime_date;

      @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
      @JoinColumn(referencedColumnName = "id", name = "movie_id", nullable = false)
      private Movie movie;
      @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
      @JoinColumn(referencedColumnName = "id", name = "auditoria_id", nullable = false)
      private Auditoria auditoria;

      @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
      @JoinColumn(referencedColumnName = "id", name = "hour_id", nullable = false)
      private Hour hour;
}
