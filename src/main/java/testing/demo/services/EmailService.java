package testing.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService{

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationCode(String email, String code){

    try{
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("Course connect verification code");
        message.setText("Your verification code is: " + code + "\n\nThis code will remain valid for 15 minutes");

        System.out.println("About to send email");
        mailSender.send(message);
    } catch (Exception e){
        e.printStackTrace();
        System.out.println("Email failed" + e.getMessage());
    }

    }

}