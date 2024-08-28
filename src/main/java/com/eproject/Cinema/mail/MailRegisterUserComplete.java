package com.eproject.Cinema.mail;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eproject.Cinema.utils.MailUtil;

@Component
public class MailRegisterUserComplete {
@Autowired
    MailUtil mailUtil;

    private final String subject = "Mail register successfully";

    public boolean sendEmail(String email, String name) {
        String body ="";
        body += "Hi" + name + ", \n";
        body += "......., \n";
        body += "\n Thanks you,\n";
        mailUtil.sendEmail(email, subject, body);
        return true;
    }
}
