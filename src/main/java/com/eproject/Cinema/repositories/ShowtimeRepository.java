package com.eproject.Cinema.repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.eproject.Cinema.entities.Showtime;

@Component
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
      // @Query("SELECT s FROM Showtime s WHERE s.movieTitle = :movieTitle AND s.suat
      // = :suat AND s.showtimeDate = :showtimeDate")
      // Showtime findByMovieTitleAndSuatAndShowtimeDate(@Param("movieTitle") String
      // movieTitle,
      // @Param("suat") String suat, @Param("showtimeDate") LocalDate showtimeDate);
}
