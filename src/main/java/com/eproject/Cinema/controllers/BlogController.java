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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eproject.Cinema.dto.BlogDTO;
import com.eproject.Cinema.dto.MovieDTO;
import com.eproject.Cinema.entities.Blog;
import com.eproject.Cinema.entities.Movie;
import com.eproject.Cinema.response.HttpResponse;
import com.eproject.Cinema.services.BlogService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/blogs")
public class BlogController extends BaseController {
      @Value("${file.upload-dir}")
      private String uploadDir;

      @Autowired
      BlogService _blogService;

      @Autowired
      HttpResponse _httpResponse;

      @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
      public ResponseEntity<?> create(@Valid BlogDTO input, @RequestParam("image") MultipartFile file,
                  BindingResult br) {
            try {
                  if (br.hasErrors()) {
                        return _httpResponse.unprocessable(getErrors(br));
                  }
                  Blog blog = new Blog();
                  if (!file.isEmpty()) {
                        input.setThumbnail(file);
                        Path path = Paths.get(uploadDir + "/blogs");
                        if (!Files.exists(path)) {
                              Files.createDirectories(path);
                        }
                        String fileName = new Timestamp(System.currentTimeMillis()).getTime()
                                    + input.getThumbnail().getOriginalFilename();
                        Path filePath = path.resolve(fileName);
                        Files.copy(input.getThumbnail().getInputStream(), filePath);
                        BeanUtils.copyProperties(input, blog);
                        System.out.println("BLOG:" + blog);
                        blog.setThumbnail(fileName);
                        Blog rs = _blogService.create(blog);

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
            return _httpResponse.success(_blogService.getAll());
      }
}
