package com.eproject.Cinema.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eproject.Cinema.dto.AuditoriaDTO;
import com.eproject.Cinema.dto.HourDTO;
import com.eproject.Cinema.entities.Auditoria;
import com.eproject.Cinema.entities.Hour;
import com.eproject.Cinema.response.HttpResponse;
import com.eproject.Cinema.services.AuditoriaService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/auditorias")
public class AuditoriaController extends BaseController {
      @Autowired
      AuditoriaService _auditoriaService;

      @Autowired
      HttpResponse _httpResponse;

      @PostMapping()
      public ResponseEntity<?> create(@Valid @RequestBody AuditoriaDTO audi, BindingResult br) {
            try {
                  if (br.hasErrors()) {
                        return _httpResponse.unprocessable(getErrors(br));
                  }
                  Auditoria ad = new Auditoria();
                  BeanUtils.copyProperties(audi, ad);
                  Auditoria rs = _auditoriaService.create(ad);
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
            return _httpResponse.success(_auditoriaService.getAll());
      }

      @GetMapping("detail/{id}")
      public ResponseEntity<?> detail(@PathVariable Long id) {
            Auditoria audi = _auditoriaService.detail(id);
            if (audi != null) {
                  return _httpResponse.success(audi);
            }
            return _httpResponse.failure();
      }

      @PutMapping("edit/{id}")
      public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody AuditoriaDTO audi, BindingResult br) {
            try {
                  if (br.hasErrors()) {
                        return _httpResponse.unprocessable(getErrors(br));
                  }
                  Auditoria auditoria = _auditoriaService.detail(id);
                  if (auditoria != null) {
                        BeanUtils.copyProperties(audi, auditoria);
                        Auditoria rs = _auditoriaService.update(auditoria);
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
      public ResponseEntity<?> delAudi(@PathVariable Long id) {
            boolean status = _auditoriaService.delete(id);
            if (status) {
                  return _httpResponse.success();
            }
            return _httpResponse.failure();
      }

}
