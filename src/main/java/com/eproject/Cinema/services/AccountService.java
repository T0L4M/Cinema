package com.eproject.Cinema.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.eproject.Cinema.entities.Account;
import com.eproject.Cinema.repositories.AccountRepository;
import com.eproject.Cinema.utils.JwtUtils;

@Service
public class AccountService {
      @Autowired
      AccountRepository _accountRepository;

      @Autowired
      JwtUtils jwtUtil;

      @Autowired
      private AuthenticationManager authenticationManager;

      public List<Account> getAll() {
            return _accountRepository.findAll();
      }

      public Account create(Account acc) {
            try {
                  return _accountRepository.save(acc);
            } catch (Exception e) {
                  e.printStackTrace();
            }
            return null;
      }

      public Account detail(Long id) {
            return _accountRepository.findById(id).get();
      }

      public Account update(Account item) {
            try {
                  return _accountRepository.save(item);
            } catch (Exception e) {
                  e.printStackTrace();
            }

            return null;
      }

      public boolean delete(Long id) {
            try {
                  Account account = _accountRepository.findById(id).get();
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
