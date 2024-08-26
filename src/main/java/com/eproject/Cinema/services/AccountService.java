package com.eproject.Cinema.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
<<<<<<< HEAD
import org.springframework.stereotype.Service;

import com.eproject.Cinema.entities.Account;
import com.eproject.Cinema.repositories.AccountRepository;
import com.eproject.Cinema.utils.JwtUtils;
=======
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eproject.Cinema.dto.LoginDTO;
import com.eproject.Cinema.entities.User;
import com.eproject.Cinema.repositories.AccountRepository;
import com.eproject.Cinema.utils.JwtUtil;
>>>>>>> 9abcf7b42e08eb1a106701d780a74d910e8a2ac6

@Service
public class AccountService {
      @Autowired
      AccountRepository _accountRepository;

      @Autowired
<<<<<<< HEAD
      JwtUtils jwtUtil;
=======
      private PasswordEncoder passwordEncoder;
>>>>>>> 9abcf7b42e08eb1a106701d780a74d910e8a2ac6

      @Autowired
      private AuthenticationManager authenticationManager;

<<<<<<< HEAD
      public List<Account> getAll() {
=======
      @Autowired
      JwtUtil jwtUtil;

      // @Autowired
      // MailRegisterUserComplete mailRegisterUserComplete;

      public List<User> getAll() {
>>>>>>> 9abcf7b42e08eb1a106701d780a74d910e8a2ac6
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

      public String login(Account acc) throws Exception {
            try {
                  Authentication authentication = authenticationManager
                              .authenticate(new UsernamePasswordAuthenticationToken(acc.getEmail(),
                              acc.getPassword()));
                  SecurityContextHolder.getContext().setAuthentication(authentication);

                  String account = authentication.getName();
                  Account acct = _accountRepository.findByAccount(account);

                  return jwtUtil.generateToken(acct);

            } catch (Exception e) {
                  throw new Exception("Email or password incorrect");
            }
      }
}
