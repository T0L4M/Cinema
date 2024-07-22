package com.eproject.Cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.eproject.Cinema.entities.Showtime;

@Component
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {

}
