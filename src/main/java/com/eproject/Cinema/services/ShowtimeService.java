package com.eproject.Cinema.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eproject.Cinema.dto.ShowtimeDTO;
import com.eproject.Cinema.entities.Showtime;
import com.eproject.Cinema.repositories.ShowtimeRepository;

@Service
public class ShowtimeService {
      @Autowired
      ShowtimeRepository _showtimeRepository;

      public List<Showtime> getAll() {
            return _showtimeRepository.findAll();
      }

      public Showtime create(Showtime show) {
            try {
                  return _showtimeRepository.save(show);
            } catch (Exception e) {
                  e.printStackTrace();
            }
            return null;
      }

      public Showtime detail(Long id) {
            return _showtimeRepository.findById(id).get();
      }

      public Showtime update(Showtime show) {
            try {
                  return _showtimeRepository.save(show);
            } catch (Exception e) {
                  e.printStackTrace();
            }
            return null;
      }

      public boolean delete(Long id) {
            try {
                  Showtime show = _showtimeRepository.findById(id).get();
                  if (show != null) {
                        _showtimeRepository.delete(show);
                        return true;
                  }
                  return false;
            } catch (Exception e) {
                  e.printStackTrace();
            }
            return false;
      }

      public List<Showtime> sortByDate() {
            return _showtimeRepository.sortByDate();
      }

      public boolean validate(ShowtimeDTO show) {
            List<Showtime> shows = getAll();
            for (Showtime showtime : shows) {
                  if (showtime.getShowtime_date().equals(show.getShowtime_date()) &&
                              showtime.getAuditoria().getId() == show.getAuditoria_id() &&
                              showtime.getHour().getId() == show.getHour_id()) {
                        return true; // Found a match for all conditions, validation failed
                  }
            }
            return false; // No match found for all conditions, validation successful
      }

      public List<Showtime> getByRoom(Long id) {
            return _showtimeRepository.getByRoom(id);
      }

      public List<Showtime> getByMovie(Long id) {
            return _showtimeRepository.getByMovie(id);
      }
}
