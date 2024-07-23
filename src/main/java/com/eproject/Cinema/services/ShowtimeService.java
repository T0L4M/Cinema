package com.eproject.Cinema.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eproject.Cinema.entities.Auditoria;
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
}
