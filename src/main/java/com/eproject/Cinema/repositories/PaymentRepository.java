package com.eproject.Cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.eproject.Cinema.entities.Payment;

@Component
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
