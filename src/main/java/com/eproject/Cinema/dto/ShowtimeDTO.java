package com.eproject.Cinema.dto;

import java.sql.Time;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowtimeDTO {
  private boolean status;

  private LocalDate showtime_date;

  private Long movie_id;

  private Long auditoria_id;

  private Long hour_id;

  private Time time_to;

  public boolean getStatus() {
    return this.status;
  }
}
