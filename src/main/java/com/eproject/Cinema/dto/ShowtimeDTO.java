package com.eproject.Cinema.dto;

import java.sql.Date;
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

  public boolean getStatus() {
    return this.status;
  }
}
