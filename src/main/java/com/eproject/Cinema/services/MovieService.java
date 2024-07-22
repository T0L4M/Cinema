package com.eproject.Cinema.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eproject.Cinema.entities.Movie;
import com.eproject.Cinema.repositories.MovieRepository;

@Service
public class MovieService {
      @Autowired
      MovieRepository _movieRepository;

      public List<Movie> getAll() {
            return _movieRepository.findAll();
      }

      public Movie create(Movie mv) {
            try {
                  return _movieRepository.save(mv);
            } catch (Exception e) {
                  e.printStackTrace();
            }

            return null;
      }

      public Movie detail(Long id) {
            return _movieRepository.findById(id).get();
      }

      public boolean delete(Long id) {
            try {
                  Movie mv = _movieRepository.findById(id).get();

                  if (mv != null) {
                        _movieRepository.delete(mv);
                        return true;
                  }
                  return false;
            } catch (Exception e) {
                  e.printStackTrace();
            }
            return false;
      }

}
