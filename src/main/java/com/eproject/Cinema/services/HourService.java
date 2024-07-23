package com.eproject.Cinema.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eproject.Cinema.entities.Hour;
import com.eproject.Cinema.repositories.HourRepository;

@Service
public class HourService {
      @Autowired
      HourRepository _hourRepository;

      public List<Hour> getAll() {
            return _hourRepository.findAll();
      }

      public Hour create(Hour item) {
            try {
                  return _hourRepository.save(item);
            } catch (Exception e) {
                  e.printStackTrace();
            }

            return null;
      }

      public Hour detail(Long id) {
            return _hourRepository.findById(id).get();
      }

      public Hour update(Hour item) {
            try {
                  return _hourRepository.save(item);
            } catch (Exception e) {
                  e.printStackTrace();
            }

            return null;
      }

      public boolean delete(Long id) {
            try {
                  Hour hour = _hourRepository.findById(id).get();
                  if (hour != null) {
                        _hourRepository.delete(hour);
                        return true;
                  }
                  return false;
            } catch (Exception e) {
                  e.printStackTrace();
            }
            return false;
      }
}
