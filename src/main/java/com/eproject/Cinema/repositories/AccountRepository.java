package com.eproject.Cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.eproject.Cinema.entities.Account;

@Component
public interface AccountRepository extends JpaRepository<Account, Long> {

}
