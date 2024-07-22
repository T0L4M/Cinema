package com.eproject.Cinema.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {
      @NotEmpty(message = "Title is required!")
      private String title;

      @NotEmpty(message = "Director is required!")
      private String director;

      private String casts;

      @NotEmpty(message = "Genre is required!")
      private String genre;

      private String description;

      private MultipartFile poster;

      @NotEmpty(message = "Release Date is required!")
      @Pattern(regexp = "^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$")
      private String release_date;
}
