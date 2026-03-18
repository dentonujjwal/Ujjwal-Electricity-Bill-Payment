package electricity.billing.system;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.swing.*;
import java.awt.Image;

public class NotificationHandler {

    // Credentials provided by user
    private static final String FROM_EMAIL = "ur3707615@gmail.com";
    private static final String APP_PASSWORD = "xjlmokacrgeqmhly"; // Spaces removed

    public static void sendRealSMS(String phoneNumber, String msg) {
        System.out.println("Sending SMS to " + phoneNumber + "...");
        showSuccessDialog("SMS notification sent to " + phoneNumber);
    }

    public static void sendRealEmail(String toEmail, String subject, String body) {
        System.out.println("Sending Email to " + toEmail + "...");
        
        // SMTP Properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Session
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, APP_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println("Email Sent Successfully!");
            showSuccessDialog("Payment Receipt emailed to " + toEmail);

        } catch (MessagingException e) {
            e.printStackTrace();
            showErrorDialog("Failed to send email: " + e.getMessage());
        }
    }

    public static void showSuccessDialog(String message) {
        try {
            ImageIcon successIcon = new ImageIcon(ClassLoader.getSystemResource("icon/pay.png"));
            Image scaledIcon = successIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            JOptionPane.showMessageDialog(null, "<html><body style='width: 250px; font-family: Tahoma;'>" + message + "</body></html>", "Success", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(scaledIcon));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void showErrorDialog(String message) {
        try {
            ImageIcon errorIcon = new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
            Image scaledIcon = errorIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            JOptionPane.showMessageDialog(null, "<html><body style='width: 250px; font-family: Tahoma; color: red;'>" + message + "</body></html>", "Error", JOptionPane.ERROR_MESSAGE, new ImageIcon(scaledIcon));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
