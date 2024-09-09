package com.eproject.Cinema.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eproject.Cinema.entities.Booking;
import com.eproject.Cinema.repositories.BookingRepository;

@Service
public class BookingService {
      @Autowired
      BookingRepository _bookingRepository;

      public List<Booking> getAll() {
            return _bookingRepository.findAll();
      }

      public Booking create(Booking book) {
            try {
                  return _bookingRepository.save(book);
            } catch (Exception e) {
                  e.printStackTrace();
            }
            return null;
      }

      public Booking detail(Long id) {
            return _bookingRepository.findById(id).get();
      }

      public Booking update(Booking book) {
            try {
                  return _bookingRepository.save(book);
            } catch (Exception e) {
                  e.printStackTrace();
            }
            return null;
      }

      public boolean delete(Long id) {
            try {
                  Booking book = _bookingRepository.findById(id).get();
                  if (book != null) {
                        _bookingRepository.delete(book);
                        return true;
                  }
                  return false;
            } catch (Exception e) {
                  e.printStackTrace();
            }
            return false;
      }

      public Booking getLatestByCustomerId(Long id) {
            List<Booking> ds = _bookingRepository.findByCustomerId(id);
            return ds.get(ds.size() - 1);
      }

      public List<Booking> getAllByShowtimeId(Long showtimeId) {
            return _bookingRepository.findByShowtimeId(showtimeId);
      }

      public boolean deleteByShowtimeIdAndCustomerId(Long showId, Long cusId) {
            List<Booking> ds = _bookingRepository.findByShowtimeIdAndCustomerId(showId, cusId);
            boolean rs = false;
            if (ds.size() > 0) {
                  Booking del = ds.get(ds.size() - 1);
                  rs = delete(del.getId());
            }
            return rs;
      }
}
