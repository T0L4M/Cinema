package com.eproject.Cinema.services;

import java.awt.image.BufferedImage;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eproject.Cinema.entities.Payment;
import com.eproject.Cinema.entities.Showtime;
import com.eproject.Cinema.mail.MailVerification;
import com.eproject.Cinema.repositories.PaymentRepository;

@Service
public class PaymentService {
      @Autowired
      PaymentRepository _paymentRepository;

      @Autowired
      MailVerification _mailVerification;

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

      public boolean sendQrCodetoMail(String email, BufferedImage barcode, String seats, Showtime showtime,
                  double amounts) {
            try {
                  boolean rs = _mailVerification.sendQrCodetoEmail(email, barcode, seats, showtime, amounts);
                  return rs;
            } catch (Exception e) {
                  e.printStackTrace();
            }
            return false;
      }

      public List<Object> paymentChart() {
            return _paymentRepository.charPayments();
      }
}