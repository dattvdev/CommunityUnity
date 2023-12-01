/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Twna21
 */
public class SendMail {

    public void sendEmail(String recipientEmail, int otp, String link, String title, String otpMSG) {
        // Sender's email credentials
        final String senderEmail = "communityunity407@gmail.com";
        final String senderPassword = "qyhi ensr ncdx xict";

        // SMTP server details (e.g., for Gmail)
        final String smtpHost = "smtp.gmail.com";
        final int smtpPort = 587;

        // Set up JavaMail properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);

        // Create a session with authentication
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create a new email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(title);

            message.setContent("<!DOCTYPE html>\n"
                    + "<html lang=\"en\">\n"
                    + "\n"
                    + "<head>\n"
                    + "    <meta charset=\"UTF-8\">\n"
                    + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "</head>\n"
                    + "\n"
                    + "<body>\n"
                    + otpMSG
                    + "\n" + "<p>Or you can use a quick <a href=\"" + link + "" + "?otp=" + otp + "&email=" + recipientEmail + "\">Link</a>:</p>"
                    + "----------------------------------------------\n"
                    + "<h3 style=\"color: blue;\">Thank you very much!</h3>\n"
                    + "<p>Organize volunteers</p>"
                    + "<p>FPT DANANG</p>"
                    + "<div style=\"text-align:center\">\n"
                    + "    <img src=\"" + "image" + "\" alt=\"alternatetext\">\n"
                    + "</div>\n"
                    + "\n"
                    + "</body>\n"
                    + "\n" + "</html>", "text/html");

            // Send the email
            //Transport.send(message);
            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Failed to send email.");
        }
    }

    public void sendEmail2(String recipientEmail, String date, String text, double amount, String name) {
        // Sender's email credentials
        final String senderEmail = "communityunity407@gmail.com";
        final String senderPassword = "qyhi ensr ncdx xict";

        // SMTP server details (e.g., for Gmail)
        final String smtpHost = "smtp.gmail.com";
        final int smtpPort = 587;

        // Set up JavaMail properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);

        // Create a session with authentication
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create a new email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("THANH TOAN THANH CONG");

            message.setContent("<!DOCTYPE html>\n"
                    + "<html lang=\"en\">\n"
                    + "\n"
                    + "<head>\n"
                    + "    <meta charset=\"UTF-8\">\n"
                    + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "</head>\n"
                    + "\n"
                    + "<body>\n"
                    + "\n" + "<p>Cam on ban da ung ho cho hoat dong: CHI TIET:</p>"
                    + "\n" + "<p>Ngay giao dich: " + date + "</p>"
                    + "\n" + "<p>Ten hoat dong: " + name + "</p>"
                    + "\n" + "<p>Noi dung: " + text + "</p>"
                    + "\n" + "<p>So tien: " + amount + "</p>"
                    + "----------------------------------------------\n"
                    + "<h3 style=\"color: blue;\">Thank you very much!</h3>\n"
                    + "<p>Organize volunteers</p>"
                    + "<p>FPT DANANG</p>"
                    + "\n"
                    + "</body>\n"
                    + "\n" + "</html>", "text/html");

            // Send the email
            //Transport.send(message);
            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Failed to send email.");
        }
    }

    public void sendEmail3(String recipientEmail, String date, String text, double amount, String name, String name2) {
        // Sender's email credentials
        final String senderEmail = "communityunity407@gmail.com";
        final String senderPassword = "qyhi ensr ncdx xict";

        // SMTP server details (e.g., for Gmail)
        final String smtpHost = "smtp.gmail.com";
        final int smtpPort = 587;

        // Set up JavaMail properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);

        // Create a session with authentication
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create a new email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("DONATION");

            message.setContent("<!DOCTYPE html>\n"
                    + "<html lang=\"en\">\n"
                    + "\n"
                    + "<head>\n"
                    + "    <meta charset=\"UTF-8\">\n"
                    + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "</head>\n"
                    + "\n"
                    + "<body>\n"
                    + "\n" + "<p>Ban nhan duoc ung ho moi: Chi tiet:</p>"
                    + "\n" + "<p>Ngay giao dich: " + date + "</p>"
                    + "\n" + "<p>Ten hoat dong: " + name + "</p>"
                    + "\n" + "<p>Nguoi ung ho: " + name2 + "</p>"
                    + "\n" + "<p>Noi dung: " + text + "</p>"
                    + "\n" + "<p>So tien: " + amount + "</p>"
                    + "----------------------------------------------\n"
                    + "<h3 style=\"color: blue;\">Thank you very much!</h3>\n"
                    + "<p>Organize volunteers</p>"
                    + "<p>FPT DANANG</p>"
                    + "\n"
                    + "</body>\n"
                    + "\n" + "</html>", "text/html");

            // Send the email
            //Transport.send(message);
            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Failed to send email.");
        }
    }

    public void sendEmail4(String recipientEmail, String name, String text) {
        // Sender's email credentials
        final String senderEmail = "communityunity407@gmail.com";
        final String senderPassword = "qyhi ensr ncdx xict";

        // SMTP server details (e.g., for Gmail)
        final String smtpHost = "smtp.gmail.com";
        final int smtpPort = 587;

        // Set up JavaMail properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);

        // Create a session with authentication
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create a new email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(senderEmail));
            message.setSubject("HELP - COMMUNITYUNITY");

            message.setContent("<!DOCTYPE html>\n"
                    + "<html lang=\"en\">\n"
                    + "\n"
                    + "<head>\n"
                    + "    <meta charset=\"UTF-8\">\n"
                    + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "</head>\n"
                    + "\n"
                    + "<body>\n"
                    + "\n" + "<p>Ban co mot tin nhan can phan hoi: Chi tiet:</p>"
                    + "\n" + "<p>Email: " + recipientEmail + "</p>"
                    + "\n" + "<p>Name: " + name + "</p>"
                    + "\n" + "<p>Noi dung: " + text + "</p>"
                    + "----------------------------------------------\n"
                    + "<h3 style=\"color: blue;\">Thank you very much!</h3>\n"
                    + "<p>Organize volunteers</p>"
                    + "<p>FPT DANANG</p>"
                    + "\n"
                    + "</body>\n"
                    + "\n" + "</html>", "text/html");

            // Send the email
            //Transport.send(message);
            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Failed to send email.");
        }
    }

}
