package com.eproject.Cinema.controllers;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eproject.Cinema.dto.AccountDTO;
<<<<<<< HEAD
import com.eproject.Cinema.entities.Account;
=======
import com.eproject.Cinema.dto.ErrorDTO;
import com.eproject.Cinema.dto.HourDTO;
import com.eproject.Cinema.dto.LoginDTO;
import com.eproject.Cinema.entities.User;
import com.eproject.Cinema.entities.Hour;
>>>>>>> 9abcf7b42e08eb1a106701d780a74d910e8a2ac6
import com.eproject.Cinema.response.HttpResponse;
import com.eproject.Cinema.services.AccountService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/accounts")
public class AccountController extends BaseController {
      @Autowired
      AccountService _accountService;

      @Autowired
      HttpResponse _httpResponse;

      @PostMapping("/login")
      public ResponseEntity<?> login(@RequestBody @Valid LoginDTO loginReq, BindingResult br)
                  throws Exception {

            try {
                  if (br.hasErrors()) {
                        return _httpResponse.unprocessable(getErrors(br));
                  }

                  String resq = _accountService.login(loginReq);
                  if (resq != null) {
                        return _httpResponse.success(resq);
                  }
                  return _httpResponse.failure();
            } catch (Exception e) {
                  return _httpResponse.failure();
            }
      }

      @PostMapping("/register")
      public ResponseEntity<?> register(@Valid @RequestBody AccountDTO userRequest, BindingResult br) {
            if (br.hasErrors()) {
                  return _httpResponse.unprocessable(getErrors(br));
            }

            User user = new User();
            BeanUtils.copyProperties(userRequest, user);
            // Write mapping class
            User rs = _accountService.create(user);
            if (rs != null) {
                  return _httpResponse.success(rs);
            }

            return _httpResponse.failure();
      }

      @GetMapping()
      public ResponseEntity<?> getList() {
            return _httpResponse.success(_accountService.getAll());
      }

      @GetMapping("detail/{id}")
      public ResponseEntity<?> detail(@PathVariable Long id) {
            User hr = _accountService.detail(id);
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
                  User account = _accountService.detail(id);
                  if (account != null) {
                        BeanUtils.copyProperties(accountDTO, account);
                        User rs = _accountService.update(account);
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
      public ResponseEntity<?> delAcc(@PathVariable Long id) {
            boolean status = _accountService.delete(id);
            if (status) {
                  return _httpResponse.success();
            }
            return _httpResponse.failure();
      }

      @PostMapping("login")
      public ResponseEntity<?> login(@PathVariable @Valid Account account, BindingResult br) throws Exception {
            try {
                  if (br.hasErrors()) {
                        return _httpResponse.unprocessable(getErrors(br));
                  }
                  String resq = _accountService.login(account);
                  if (resq != null) {
                        return _httpResponse.success(resq);
                  } 
                  return _httpResponse.failure();
            } catch (Exception e) {
                  return _httpResponse.failure();

            }
      }

}
