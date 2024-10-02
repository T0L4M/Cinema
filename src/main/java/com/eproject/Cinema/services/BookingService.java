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

      public Booking findByShowtimeIdAndCustomerId(Long showId, Long cusId) {
            List<Booking> ds = _bookingRepository.findByShowtimeIdAndCustomerId(showId, cusId);
            if (ds.size() > 0) {
                  return ds.get(ds.size() - 1);
            }
            return null;
      }

      public List<Object> getTopMovieChart() {
            return _bookingRepository.topMovieChart();
      }

      public String convertSeats(String seatCodes) {
            StringBuilder convertedSeats = new StringBuilder();
            String[] seatList = seatCodes.split(" ");

            for (String seatCode : seatList) {
                  if (!isValidSeatCode(seatCode)) {
                        continue; // Skip invalid seat codes
                  }

                  String[] parts = seatCode.split("x");
                  int row = Integer.parseInt(parts[0]);
                  int column = Integer.parseInt(parts[1]);

                  char rowChar = (char) ('A' + row - 1);
                  convertedSeats.append(rowChar).append(column).append(", ");
            }

            return convertedSeats.toString().trim();
      }

      private boolean isValidSeatCode(String seatCode) {
            return seatCode.contains("x") && // Check for "x" separator
                        Character.isDigit(seatCode.charAt(0)) && // Check if first character is a digit
                        Character.isDigit(seatCode.charAt(seatCode.length() - 1)); // Check if last character is a digit
      }
}
