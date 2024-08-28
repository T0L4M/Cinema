package com.eproject.Cinema.services;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.eproject.Cinema.entities.User;
import com.eproject.Cinema.entities.Verification;
import com.eproject.Cinema.filter.PasswordGenerator;
import com.eproject.Cinema.mail.MailVerification;
import com.eproject.Cinema.repositories.VerificationRepository;

@Service
public class VerificationService {
    @Value("${spring.token.expiredTime}")
    public String expiredTime;

    @Autowired
    VerificationRepository verificationRepository;

    @Autowired
    MailVerification mailVerification;

    @Autowired
    AccountService _accountService;

    

    public boolean create(String email) {
        try {
            Verification verification = new Verification();
            verification.setEmail(email);
            verification.setToken(generateToken(6));
            int time = (expiredTime != null && expiredTime != "") ? Integer.parseInt(expiredTime) : 5;
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(new Date().getTime());
            calendar.add(Calendar.MINUTE, time);
            verification.setExpiredAt(new Timestamp(calendar.getTime().getTime()));
            verificationRepository.save(verification);

            mailVerification.sendEmail(email, verification.getToken());
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean changePassword(String tokenCode) {
        try {
            Verification veri = verificationRepository.findByToken(tokenCode);
            if (veri != null) {
                User user = _accountService.getByEmail(veri.getEmail());
                if (user != null) {
                    String newPassword = PasswordGenerator.generatePassword();
                    user.setPassword(newPassword);
                    User rs = _accountService.update(user);
                    if (rs != null) {
                        mailVerification.sendPasstoEmail(user.getEmail(), newPassword);
                        return true;
                    }
                }
                
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean verifyToken(String token) {
        try {
            Verification verification = verificationRepository.findByToken(token);
            if (verification != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(new Date().getTime());
                Timestamp now = new Timestamp(calendar.getTime().getTime());
                if (now.before(verification.getExpiredAt())) {
                    return true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private String generateToken(int length) {
        // Create an instance of Random class
        Random random = new Random();

        // Generate a random integer between 0 (inclusive) and 1000000 (exclusive)
        int randomNum = random.nextInt(900000) + 100000;
        return randomNum + "";
    }

    public Verification findByCode(String code) {
        return verificationRepository.findByToken(code);
    }

}
