package com.eproject.Cinema.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.eproject.Cinema.entities.Showtime;

@Component
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
      @Query("SELECT s FROM Showtime s ORDER BY showtime_date ASC, s.hour.time_from ASC")
      public List<Showtime> sortByDate();


      @Query("SELECT s FROM Showtime s WHERE s.auditoria.id = :roomId ")
      public List<Showtime> getByRoom(@Param("roomId") Long roomId);

     
}
