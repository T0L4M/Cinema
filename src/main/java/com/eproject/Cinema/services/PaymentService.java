package com.eproject.Cinema.services;

import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.eproject.Cinema.configs.ConfigVNPay;
import com.eproject.Cinema.dto.PaymentDTO;
import com.eproject.Cinema.entities.Payment;
import com.eproject.Cinema.entities.Showtime;
import com.eproject.Cinema.mail.MailVerification;
import com.eproject.Cinema.repositories.PaymentRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class PaymentService {
      @Autowired
      private HttpServletRequest request;

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

      public List<Payment> getByCustomerId(Long id) {
            return _paymentRepository.getPaymentByUserId(id);
      }

      public String createpaymentVnpay2(PaymentDTO payment) throws UnsupportedEncodingException {
            String orderType = "other";
            long amount = (long) (payment.getAmount() * 100);

            String vnp_TxnRef = ConfigVNPay.getRandomNumber(8);

            String vnp_TmnCode = ConfigVNPay.vnp_TmnCode;

            Map<String, String> vnp_Params = new HashMap<>();
            vnp_Params.put("vnp_Amount", String.valueOf(amount));
            vnp_Params.put("vnp_BankCode", "NCB");

            vnp_Params.put("vnp_Version", ConfigVNPay.vnp_Version);
            vnp_Params.put("vnp_Command", ConfigVNPay.vnp_Command);
            vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
            vnp_Params.put("vnp_CurrCode", "VND");
            vnp_Params.put("vnp_Locale", "vn");
            vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
            vnp_Params.put("vnp_OrderInfo", vnp_TxnRef);
            vnp_Params.put("vnp_OrderType", orderType);
            String vnp_ReturnUrl = ConfigVNPay.vnp_ReturnUrl2;
            // logic mobie
            // ==================================================================================

            // vnp_ReturnUrl += "?showtimeId=" + paymentRequest.getShowtimeId();
            // if (paymentRequest.getQuantitySeat() != null &
            // !paymentRequest.getQuantitySeat().isEmpty()) {
            // vnp_ReturnUrl += "&quantitySeat=" + String.join(",",
            // paymentRequest.getQuantitySeat());
            // }
            // if (paymentRequest.getQuantityDoubleSeat() != null &
            // !paymentRequest.getQuantityDoubleSeat().isEmpty()) {
            // vnp_ReturnUrl += "&quantityDoubleSeat=" + String.join(",",
            // paymentRequest.getQuantityDoubleSeat());
            // }
            // if (paymentRequest.getVoucherId() != null) {
            // vnp_ReturnUrl += "&voucherId=" + paymentRequest.getVoucherId();
            // }

            // if (paymentRequest.getQuantityWater() != null &&
            // !paymentRequest.getQuantityWater().isEmpty()) {
            // List<BookingWaterCorn> waterCorns = new ArrayList<>();

            // paymentRequest.getQuantityWater().forEach(item -> {
            // BookingWaterCorn waterCorn = new BookingWaterCorn();
            // waterCorn.setQuantity(item.getQuantity());
            // waterCorn.setWaterCorn(waterCornRepository.findById(item.getId()).get());
            // waterCorn.setBooking(null);
            // waterCorns.add(waterCorn);
            // });

            // List<BookingWaterCorn> savedWaterCorns =
            // bookingWaterRepository.saveAll(waterCorns);

            // List<String> bookingWaterCornIds = savedWaterCorns.stream()
            // .map(bookingWaterCorn -> String.valueOf(bookingWaterCorn.getId()))
            // .collect(Collectors.toList());

            // vnp_ReturnUrl += "&quantityWater=" + String.join(",", bookingWaterCornIds);
            // }
            // UserDetails userDetails = (UserDetails)
            // SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            // User user = userRepository
            // .findByEmail(userDetails.getUsername())
            // .orElseThrow(() -> new AppException(NOT_FOUND));

            // vnp_ReturnUrl += "&userId=" + user.getId();

            // end mobie
            // =================================================================================================

            vnp_Params.put("vnp_ReturnUrl", vnp_ReturnUrl);
            vnp_Params.put("vnp_IpAddr", request.getRemoteAddr());

            Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String vnp_CreateDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

            cld.add(Calendar.MINUTE, 15);
            String vnp_ExpireDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

            List fieldNames = new ArrayList(vnp_Params.keySet());
            Collections.sort(fieldNames);
            StringBuilder hashData = new StringBuilder();
            StringBuilder query = new StringBuilder();
            Iterator itr = fieldNames.iterator();
            while (itr.hasNext()) {
                  String fieldName = (String) itr.next();
                  String fieldValue = vnp_Params.get(fieldName);
                  if ((fieldValue != null) && (fieldValue.length() > 0)) {
                        // Build hash data
                        hashData.append(fieldName);
                        hashData.append('=');
                        hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
                        // Build query
                        query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII));
                        query.append('=');
                        query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
                        if (itr.hasNext()) {
                              query.append('&');
                              hashData.append('&');
                        }
                  }
            }
            String queryUrl = query.toString();
            String vnp_SecureHash = ConfigVNPay.hmacSHA512(ConfigVNPay.secretKey, hashData.toString());
            queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;

            String paymentUrl = ConfigVNPay.vnp_PayUrl + "?" + queryUrl;

            return paymentUrl;
      }

}