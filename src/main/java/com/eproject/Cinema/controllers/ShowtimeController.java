package com.eproject.Cinema.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eproject.Cinema.dto.ErrorDTO;
import com.eproject.Cinema.dto.ShowtimeDTO;
import com.eproject.Cinema.entities.Auditoria;
import com.eproject.Cinema.entities.Hour;
import com.eproject.Cinema.entities.Movie;
import com.eproject.Cinema.entities.Showtime;
import com.eproject.Cinema.response.HttpResponse;
import com.eproject.Cinema.services.AuditoriaService;
import com.eproject.Cinema.services.HourService;
import com.eproject.Cinema.services.MovieService;
import com.eproject.Cinema.services.ShowtimeService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/showtimes")
public class ShowtimeController extends BaseController {
      @Autowired
      ShowtimeService _showtimeService;

      @Autowired
      MovieService _movieService;

      @Autowired
      HourService _hourService;

      @Autowired
      AuditoriaService _auditoriaService;

      @Autowired
      HttpResponse _httpResponse;

      @PostMapping()
      public ResponseEntity<?> create(@Valid @RequestBody ShowtimeDTO showtimeDTO, BindingResult br) {
            try {
                  if (br.hasErrors()) {
                        return _httpResponse.unprocessable(getErrors(br));
                  }
                  Movie movie = _movieService.detail(showtimeDTO.getMovie_id());
                  Auditoria audi = _auditoriaService.detail(showtimeDTO.getAuditoria_id());
                  Hour hour = _hourService.detail(showtimeDTO.getHour_id());
                  if (movie != null && audi != null && hour != null) {
                        Showtime show = new Showtime();
                        show.setShowtime_date(showtimeDTO.getShowtime_date());
                        show.setHour(hour);
                        show.setAuditoria(audi);
                        show.setMovie(movie);
                        Showtime rs = _showtimeService.create(show);
                        if (rs != null) {
                              return _httpResponse.success(rs);
                        }
                        return _httpResponse.failure();
                  }
                  List<ErrorDTO> errors = new ArrayList();
                  errors.add(new ErrorDTO("code", "Invalid category"));
                  return _httpResponse.unprocessable(errors);
            } catch (Exception e) {
                  return _httpResponse.failure();
            }
      }

      @GetMapping()
      public ResponseEntity<?> getList() {
            return _httpResponse.success(_showtimeService.getAll());
      }

      @GetMapping("detail/{id}")
      public ResponseEntity<?> detail(@PathVariable Long id) {
            Showtime show = _showtimeService.detail(id);
            if (show != null) {
                  return _httpResponse.success(show);
            }
            return _httpResponse.failure();
      }

      @PutMapping("edit/{id}")
      public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ShowtimeDTO showtimeDTO,
                  BindingResult br) {
            try {
                  if (br.hasErrors()) {
                        return _httpResponse.unprocessable(getErrors(br));
                  }
                  Movie movie = _movieService.detail(showtimeDTO.getMovie_id());
                  Auditoria audi = _auditoriaService.detail(showtimeDTO.getAuditoria_id());
                  Hour hour = _hourService.detail(showtimeDTO.getHour_id());
                  Showtime show = _showtimeService.detail(id);
                  if (show != null) {
                        show.setShowtime_date(showtimeDTO.getShowtime_date());
                        show.setHour(hour);
                        show.setAuditoria(audi);
                        show.setMovie(movie);
                        Showtime rs = _showtimeService.update(show);
                        if (rs != null) {
                              return _httpResponse.success(rs);
                        }
                  }
            } catch (Exception e) {
                  return _httpResponse.failure();
            }
            return _httpResponse.failure();
      }

      // @DeleteMapping("delete/{id}")
      // public ResponseEntity<?> delAudi(@PathVariable Long id) {
      // boolean status = _showtimeService.delete(id);
      // if (status) {
      // return _httpResponse.success();
      // }
      // return _httpResponse.failure();
      // }
}
