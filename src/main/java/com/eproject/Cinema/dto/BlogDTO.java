package com.eproject.Cinema.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogDTO {
      @NotEmpty(message = "Title is required!")
      private String title;
      private String content;
      private boolean status;
      private MultipartFile thumbnail;
}
