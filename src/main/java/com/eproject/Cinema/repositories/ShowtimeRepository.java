package com.eproject.Cinema.repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.eproject.Cinema.entities.Showtime;

@Component
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
      @Query("SELECT s FROM Showtime s WHERE status = true ORDER BY showtime_date")
      public List<Showtime> sortByDate();

      @Query("SELECT s FROM Showtime s WHERE s.auditoria.id = :roomId ")
      public List<Showtime> getByRoom(@Param("roomId") Long roomId);

      @Query("SELECT s FROM Showtime s WHERE s.movie.id = :movieId AND s.status = true")
      public List<Showtime> getByMovie(@Param("movieId") Long movieId);

      @Query("SELECT s FROM Showtime s WHERE s.showtime_date < :date AND s.status = true")
      List<Showtime> findPastShowtimes(@Param("date") LocalDate date);
}
