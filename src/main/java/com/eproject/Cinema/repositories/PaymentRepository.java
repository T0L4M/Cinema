package com.eproject.Cinema.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.eproject.Cinema.entities.Payment;

@Component
public interface PaymentRepository extends JpaRepository<Payment, Long> {
      @Query(value = "SELECT DATE_FORMAT(created_at, '%Y-%m-%d') AS date, sum(amount) AS amount FROM tb_payments GROUP BY DATE_FORMAT(created_at, '%Y-%m-%d')", nativeQuery = true)
      List<Object> charPayments();

      @Query(value = "SELECT p.* FROM tb_payments p JOIN tb_bookings b ON p.booking_id = b.id WHERE b.customer_id = ?", nativeQuery = true)
      List<Payment> getPaymentByUserId(Long id);
}
