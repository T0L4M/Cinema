package com.eproject.Cinema.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eproject.Cinema.entities.Hour;
import com.eproject.Cinema.entities.Payment;
import com.eproject.Cinema.repositories.PaymentRepository;

@Service
public class PaymentService {
      @Autowired
      PaymentRepository _paymentRepository;

      public List<Payment> getAll() {
            return _paymentRepository.findAll();
      }

      public Payment create(Payment item) {
            try {
                  return _paymentRepository.save(item);
            } catch (Exception e) {
                  e.printStackTrace();
            }
            return null;
      }

      public Payment detail(Long id) {
            return _paymentRepository.findById(id).get();
      }

      public Payment update(Payment item) {
            try {
                  return _paymentRepository.save(item);
            } catch (Exception e) {
                  e.printStackTrace();
            }

            return null;
      }

      public boolean delete(Long id) {
            try {
                  Payment payment = _paymentRepository.findById(id).get();
                  if (payment != null) {
                        _paymentRepository.delete(payment);
                        return true;
                  }
                  return false;
            } catch (Exception e) {
                  e.printStackTrace();
            }
            return false;
      }
}
