package com.eproject.Cinema.mail;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eproject.Cinema.utils.MailUtil;

@Component
public class MailVerification {
    @Autowired
    MailUtil mailUtil;

    private final String subject = "Mail verifycation";

    public boolean sendEmail(String email, String token) {
        String body ="";
        body += "Hi guy, \n";
        body += "Please using code for resetting account, \n";
        body += token + "\n";
        body += "\n Thanks you,\n";
        mailUtil.sendEmail(email, subject, body);
        return true;
    }

    public boolean sendPasstoEmail(String email, String password) {
        String body ="";
        body += "Hi guy, \n";
        body += "Please using password for login your account, \n";
        body += password + "\n";
        body += "\n Thanks you,\n";
        mailUtil.sendEmail(email, subject, body);
        return true;
    }
}
