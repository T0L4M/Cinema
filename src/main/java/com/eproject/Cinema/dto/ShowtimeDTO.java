package com.eproject.Cinema.dto;

import java.sql.Date;

import com.eproject.Cinema.entities.Auditoria;
import com.eproject.Cinema.entities.Hour;
import com.eproject.Cinema.entities.Movie;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowtimeDTO {
  private boolean status;

  private Date showtime_date;

  private Long movie_id;

  private Long auditoria_id;

  private Long hour_id;
}
