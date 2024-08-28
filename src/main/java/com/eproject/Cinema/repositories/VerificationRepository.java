package com.eproject.Cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.eproject.Cinema.entities.Verification;

@Component
public interface VerificationRepository extends JpaRepository<Verification, Long> {
    public Verification findByToken(String token);

}
