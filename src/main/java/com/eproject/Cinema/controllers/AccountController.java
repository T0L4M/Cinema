package com.eproject.Cinema.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eproject.Cinema.dto.AccountDTO;
import com.eproject.Cinema.dto.LoginDTO;
import com.eproject.Cinema.dto.NewPasswordDTO;
import com.eproject.Cinema.entities.User;
import com.eproject.Cinema.request.ForgotRequest;
import com.eproject.Cinema.request.MailRequest;
import com.eproject.Cinema.response.HttpResponse;
import com.eproject.Cinema.services.AccountService;
import com.eproject.Cinema.services.VerificationService;

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

      @Autowired
      VerificationService _verificationService;

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

      @PutMapping("changePassword")
      public ResponseEntity<?> changePasswordWithEmail(@Valid @RequestBody NewPasswordDTO accountDTO,
                  BindingResult br) {
            try {
                  if (br.hasErrors()) {
                        return _httpResponse.unprocessable(getErrors(br));
                  }
                  User account = _accountService.getByEmail(accountDTO.getEmail());
                  if (account != null) {
                        account.setPassword(accountDTO.getNewPassword());
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

      @PostMapping("send_mail")
      public ResponseEntity<?> sendMail(@Valid @RequestBody MailRequest mailRequest, BindingResult br) {

            if (br.hasErrors()) {
                  return _httpResponse.unprocessable(getErrors(br));
            }

            boolean status = _verificationService.create(mailRequest.getEmail());

            if (status) {
                  return _httpResponse.success();
            }

            return _httpResponse.failure();
      }

      @PostMapping("forgot")
      public ResponseEntity<?> forgotPass(@Valid @RequestBody ForgotRequest forgotRequest,
                  BindingResult br) {
            try {
                  if (br.hasErrors()) {
                        return _httpResponse.unprocessable(getErrors(br));
                  }

                  boolean isVerify = _verificationService.verifyToken(
                              forgotRequest.getCode());

                  if (!isVerify) {
                        return _httpResponse.unprocessable(getErrors(br));
                  }

                  boolean rs = _verificationService.changePassword(forgotRequest.getCode());
                  if (rs) {

                        return _httpResponse.success();
                  }
                  return _httpResponse.failure();

            } catch (Exception e) {
                  return _httpResponse.failure();
            }
      }

}
