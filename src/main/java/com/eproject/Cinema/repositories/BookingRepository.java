package com.eproject.Cinema.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.eproject.Cinema.entities.Booking;

@Component
public interface BookingRepository extends JpaRepository<Booking, Long> {
      public List<Booking> findByShowtimeId(Long showtimeId);

      public List<Booking> findByCustomerId(Long customerId);

      public List<Booking> findByShowtimeIdAndCustomerId(Long showId, Long cusId);

      @Query(value = "SELECT SUM(b.quantity) AS total_quantity, m.title " +
                  "FROM tb_movies m " +
                  "LEFT JOIN tb_showtimes s ON m.id = s.movie_id " +
                  "LEFT JOIN tb_bookings b ON s.id = b.showtime_id " +
                  "WHERE m.status = 'Showing'" +
                  "GROUP BY m.title " +
                  "ORDER BY total_quantity DESC", nativeQuery = true)
      List<Object> topMovieChart();

}
