package com.eproject.Cinema.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eproject.Cinema.dto.HourDTO;
import com.eproject.Cinema.entities.Hour;
import com.eproject.Cinema.response.HttpResponse;
import com.eproject.Cinema.services.HourService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/hours")
public class HourController extends BaseController {
      @Autowired
      HourService _hourService;

      @Autowired
      HttpResponse _httpResponse;

      @PostMapping()
      public ResponseEntity<?> create(@Valid @RequestBody HourDTO hour, BindingResult br) {
            try {
                  if (br.hasErrors()) {
                        return _httpResponse.unprocessable(getErrors(br));
                  }
                  if (hour.getTime_from().compareTo(hour.getTime_to()) >= 0) {
                        // Return a response indicating the error
                        return _httpResponse.failure("time_from must be before time_to");
                  }
                  Hour hr = new Hour();
                  BeanUtils.copyProperties(hour, hr);
                  Hour rs = _hourService.create(hr);
                  if (rs != null) {
                        return _httpResponse.success(rs);
                  }
                  return _httpResponse.failure();
            } catch (Exception e) {
                  return _httpResponse.failure();
            }
      }

      @GetMapping()
      public ResponseEntity<?> getList() {
            return _httpResponse.success(_hourService.getAll());
      }

      @GetMapping("detail/{id}")
      public ResponseEntity<?> detail(@PathVariable Long id) {
            Hour hr = _hourService.detail(id);
            if (hr != null) {
                  return _httpResponse.success(hr);
            }
            return _httpResponse.failure();
      }

      @PutMapping("edit/{id}")
      public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody HourDTO hour, BindingResult br) {
            try {
                  if (br.hasErrors()) {
                        return _httpResponse.unprocessable(getErrors(br));
                  }
                  Hour hr = _hourService.detail(id);
                  if (hr != null) {
                        BeanUtils.copyProperties(hour, hr);
                        Hour rs = _hourService.update(hr);
                        if (rs != null) {
                              return _httpResponse.success(rs);
                        }
                  }
            } catch (Exception e) {
                  return _httpResponse.failure();
            }
            return _httpResponse.failure();
      }

      @DeleteMapping("delete/{id}")
      public ResponseEntity<?> delHour(@PathVariable Long id) {
            boolean status = _hourService.delete(id);
            if (status) {
                  return _httpResponse.success();
            }
            return _httpResponse.failure();
      }
}
