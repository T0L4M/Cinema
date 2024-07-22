package com.eproject.Cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.eproject.Cinema.entities.Movie;

@Component
public interface MovieRepository extends JpaRepository<Movie, Long> {

}
