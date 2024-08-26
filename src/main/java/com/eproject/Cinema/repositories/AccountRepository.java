package com.eproject.Cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.eproject.Cinema.entities.User;

@Component
<<<<<<< HEAD
public interface AccountRepository extends JpaRepository<Account, Long> {
    public Account findByAccount(String account);
    
=======
public interface AccountRepository extends JpaRepository<User, Long> {
      public User findByUserName(String userName);
>>>>>>> 9abcf7b42e08eb1a106701d780a74d910e8a2ac6
}
