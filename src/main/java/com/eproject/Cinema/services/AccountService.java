package com.eproject.Cinema.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eproject.Cinema.dto.LoginDTO;
import com.eproject.Cinema.entities.User;
import com.eproject.Cinema.repositories.AccountRepository;
import com.eproject.Cinema.utils.JwtUtil;

@Service
public class AccountService {
      @Autowired
      AccountRepository _accountRepository;

      @Autowired
      private PasswordEncoder passwordEncoder;

      @Autowired
      private AuthenticationManager authenticationManager;

      @Autowired
      JwtUtil jwtUtil;

      // @Autowired
      // MailRegisterUserComplete mailRegisterUserComplete;

      public List<User> getAll() {
            return _accountRepository.findAll();
      }

      public User create(User user) {

            try {
                  user.setPassword(passwordEncoder.encode(user.getPassword()));
                  _accountRepository.save(user);
                  // mailRegisterUserComplete.sendEmail(user.getEmail(), user.getFirstName() + " "
                  // + user.getLastName());

                  return user;
            } catch (Exception e) {
                  e.printStackTrace();
            }
            return null;

      }

      public String login(LoginDTO loginReq) throws Exception {
            try {
                  Authentication authentication = authenticationManager
                              .authenticate(
                                          new UsernamePasswordAuthenticationToken(loginReq.getUserName(),
                                                      loginReq.getPassword()));
                  SecurityContextHolder.getContext().setAuthentication(authentication);

                  String userName = authentication.getName();
                  User user = _accountRepository.findByUserName(userName);

                  return jwtUtil.generateToken(user);

            } catch (Exception e) {
                  throw new Exception("User name or password incorrect");
            }
      }

      public User detail(Long id) {
            return _accountRepository.findById(id).get();
      }

      public User update(User item) {
            try {
                  return _accountRepository.save(item);
            } catch (Exception e) {
                  e.printStackTrace();
            }

            return null;
      }

      public boolean delete(Long id) {
            try {
                  User account = _accountRepository.findById(id).get();
                  if (account != null) {
                        _accountRepository.delete(account);
                        return true;
                  }
                  return false;
            } catch (Exception e) {
                  e.printStackTrace();
            }
            return false;
      }
}
