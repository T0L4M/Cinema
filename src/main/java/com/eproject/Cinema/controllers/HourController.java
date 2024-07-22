package com.eproject.Cinema.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eproject.Cinema.entities.Hour;
import com.eproject.Cinema.response.HttpResponse;
import com.eproject.Cinema.services.HourService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/hours")
public class HourController extends BaseController {
      @Autowired
      HourService _hourService;

      @Autowired
      HttpResponse _httpResponse;

      @PostMapping()
      public ResponseEntity<?> create(@Valid @RequestBody Hour hr, BindingResult br) {
            if (br.hasErrors()) {
                  return _httpResponse.unprocessable(getErrors(br));
            }
            Hour rs = _hourService.create(hr);
            if (rs != null) {
                  return _httpResponse.success(rs);
            }
            return _httpResponse.failure();
      }

      @GetMapping()
      public ResponseEntity<?> getList() {
            return _httpResponse.success(_hourService.getAll());
      }
}
