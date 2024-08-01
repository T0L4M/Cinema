package com.eproject.Cinema.entities;

import java.sql.Date;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "tb_showtimes")
public class Showtime extends BaseEntity {
      private boolean status;

      @Temporal(TemporalType.DATE)
      private Date showtime_date;

      @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
      @JoinColumn(referencedColumnName = "id", name = "movie_id", nullable = false)
      private Movie movie;

      @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
      @JoinColumn(referencedColumnName = "id", name = "auditoria_id", nullable = false)
      private Auditoria auditoria;

      @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
      @JoinColumn(referencedColumnName = "id", name = "hour_id", nullable = false)
      private Hour hour;

      @OneToMany(mappedBy = "showtime", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
      @Transient
      @OnDelete(action = OnDeleteAction.CASCADE)
      private List<Booking> bookings;
}
