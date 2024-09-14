package com.eproject.Cinema.mail;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eproject.Cinema.entities.Showtime;
import com.eproject.Cinema.utils.MailUtil;

import jakarta.activation.DataSource;
import jakarta.mail.util.ByteArrayDataSource;

@Component
public class MailVerification {
    @Autowired
    MailUtil mailUtil;

    private final String subject = "Mail verifycation";

    public boolean sendEmail(String email, String token) {
        String body = "";
        body += "Hi guy, \n";
        body += "Please using code for resetting account, \n";
        body += token + "\n";
        body += "\n Thanks you,\n";
        mailUtil.sendEmail(email, subject, body);
        return true;
    }

    public boolean sendPasstoEmail(String email, String password) {
        String body = "";
        body += "Hi guy, \n";
        body += "Please using password for login your account, \n";
        body += password + "\n";
        body += "\n Thanks you,\n";
        mailUtil.sendEmail(email, subject, body);
        return true;
    }

    public boolean sendQrCodetoEmail(String email, BufferedImage barcode, String seats, Showtime showtime,
            double amounts) {

        String body = "";
        body += "Chào bạn, \n";
        body += "Bạn thanh toán xong rồi này, \n";
        body += "Thông tin vé:  \n";
        body += "Số ghế:" + seats + "\n";
        body += "Suất chiếu:" + showtime.getMovie().getTitle() + " - " + showtime.getHour().getTime_from() + " - "
                + showtime.getAuditoria().getName() + "\n";
        body += String.format("Tổng tiền: %.0f VND\n", amounts);
        body += "Đây là mã QR thông tin thanh toán của bạn trong file đính kèm.\n";
        body += "\nThanks you,\n";

        try {
            // Chuyển đổi BufferedImage thành byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(barcode, "png", baos);
            byte[] imageData = baos.toByteArray();

            // Tạo DataSource cho hình ảnh
            DataSource dataSource = new ByteArrayDataSource(imageData, "image/png");

            // Gửi email với file đính kèm
            mailUtil.sendEmailWithAttachment(email, "Your QR Code", body, dataSource, "qr_code.png");

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
