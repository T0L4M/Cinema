package com.eproject.Cinema.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eproject.Cinema.dto.AccountDTO;
import com.eproject.Cinema.dto.HourDTO;
import com.eproject.Cinema.entities.Account;
import com.eproject.Cinema.entities.Hour;
import com.eproject.Cinema.response.HttpResponse;
import com.eproject.Cinema.services.AccountService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/accounts")
public class AccountController extends BaseController {
      @Autowired
      AccountService _accountService;

      @Autowired
      HttpResponse _httpResponse;

      @PostMapping()
      public ResponseEntity<?> create(@Valid @RequestBody AccountDTO accountDTO, BindingResult br) {
            try {
                  if (br.hasErrors()) {
                        return _httpResponse.unprocessable(getErrors(br));
                  }
                  Account acc = new Account();
                  BeanUtils.copyProperties(accountDTO, acc);
                  Account rs = _accountService.create(acc);
                  if (rs != null) {
                        return _httpResponse.success();
                  }
                  return _httpResponse.failure();
            } catch (Exception e) {
                  return _httpResponse.failure();
            }
      }

      @GetMapping()
      public ResponseEntity<?> getList() {
            return _httpResponse.success(_accountService.getAll());
      }

      @GetMapping("detail/{id}")
      public ResponseEntity<?> detail(@PathVariable Long id) {
            Account hr = _accountService.detail(id);
            if (hr != null) {
                  return _httpResponse.success(hr);
            }
            return _httpResponse.failure();
      }

      @PutMapping("edit/{id}")
      public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody AccountDTO accountDTO,
                  BindingResult br) {
            try {
                  if (br.hasErrors()) {
                        return _httpResponse.unprocessable(getErrors(br));
                  }
                  Account account = _accountService.detail(id);
                  if (account != null) {
                        BeanUtils.copyProperties(accountDTO, account);
                        Account rs = _accountService.update(account);
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
            boolean status = _accountService.delete(id);
            if (status) {
                  return _httpResponse.success();
            }
            return _httpResponse.failure();
      }

}
