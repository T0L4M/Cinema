package com.eproject.Cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.eproject.Cinema.entities.User;

@Component
public interface AccountRepository extends JpaRepository<User, Long> {
      public User findByUserName(String userName);
      public User findByEmail(String email);
      
}
