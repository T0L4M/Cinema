package com.eproject.Cinema.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eproject.Cinema.dto.MovieDTO;
import com.eproject.Cinema.entities.Movie;

import com.eproject.Cinema.response.HttpResponse;
import com.eproject.Cinema.services.MovieService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/movies")
public class MovieController extends BaseController {
      @Value("${file.upload-dir}")
      private String uploadDir;

      @Autowired
      MovieService _movieService;

      @Autowired
      HttpResponse _httpResponse;

      // ============METHOD============

      @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
      public ResponseEntity<?> create(@Valid MovieDTO mv, @RequestParam("image") MultipartFile file,
                  BindingResult br) {
            try {
                  if (br.hasErrors()) {
                        return _httpResponse.unprocessable(getErrors(br));
                  }
                  Movie movie = new Movie();
                  if (!file.isEmpty()) {
                        mv.setPoster(file);
                        Path path = Paths.get(uploadDir + "/movies");
                        if (!Files.exists(path)) {
                              Files.createDirectories(path);
                        }
                        String fileName = new Timestamp(System.currentTimeMillis()).getTime()
                                    + mv.getPoster().getOriginalFilename();
                        Path filePath = path.resolve(fileName);
                        Files.copy(mv.getPoster().getInputStream(), filePath);
                        BeanUtils.copyProperties(mv, movie);
                        movie.setRelease_date(Date.valueOf(mv.getRelease_date()));
                        movie.setPoster(fileName);
                        Movie rs = _movieService.create(movie);

                        if (rs != null) {
                              return _httpResponse.success(rs);
                        }
                  }
                  return _httpResponse.failure();
            } catch (Exception e) {
                  return _httpResponse.failure();
            }
      }

      @GetMapping()
      public ResponseEntity<?> getList() {
            return _httpResponse.success(_movieService.getAll());
      }

      @PutMapping(path = "edit/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
      public ResponseEntity<?> update(@PathVariable Long id, @Valid MovieDTO mv,
                  @RequestParam(name = "image", required = false) MultipartFile file, BindingResult br) {

            if (br.hasErrors()) {
                  return _httpResponse.unprocessable(getErrors(br));
            }
            Movie movie = _movieService.detail(id);
            if (movie != null) {
                  if (!file.isEmpty()) {
                        try {
                              mv.setPoster(file);
                              Path path = Paths.get(uploadDir, "movies");
                              Path filepath = Paths.get(uploadDir, "movies", movie.getPoster());
                              Files.delete(filepath);
                              String fileName = new Timestamp(System.currentTimeMillis()).getTime()
                                          + mv.getPoster().getOriginalFilename();

                              Path filePath = path.resolve(fileName);
                              Files.copy(mv.getPoster().getInputStream(), filePath);
                              movie.setPoster(fileName);
                        } catch (Exception e) {
                              return _httpResponse.failure();
                        }
                  }
                  BeanUtils.copyProperties(mv, movie);
                  movie.setRelease_date(Date.valueOf(mv.getRelease_date()));
                  Movie rs = _movieService.create(movie);
                  System.out.println("####################MOVIE: " + movie.getTitle() + "###############");
                  if (rs != null) {
                        return _httpResponse.success(rs);
                  }
            }
            return _httpResponse.failure();

      }

      @DeleteMapping("delete/{id}")
      public ResponseEntity<?> delete(@PathVariable Long id) {
            Movie rs = _movieService.detail(id);
            if (rs != null) {
                  try {
                        Path filepath = Paths.get(uploadDir, "movies", rs.getPoster());
                        Files.delete(filepath);
                        boolean result = _movieService.delete(id);
                        if (result) {
                              return _httpResponse.success(result);
                        }
                        return _httpResponse.failure();
                  } catch (Exception e) {
                        return _httpResponse.failure();
                  }
            }
            return _httpResponse.failure();
      }

      @GetMapping("detail/{id}")
      public ResponseEntity<?> detail(@PathVariable Long id) {
            Movie mv = _movieService.detail(id);
            if (mv != null) {
                  return _httpResponse.success(mv);
            }
            return _httpResponse.failure();
      }
}
