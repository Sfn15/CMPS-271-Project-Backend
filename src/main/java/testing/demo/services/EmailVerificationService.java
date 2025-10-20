package testing.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testing.demo.services.EmailService;
import java.time.Instant;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EmailVerificationService {

    @Autowired
    private EmailService emailService;
    
    private static class VerificationData{
        String code;
        Instant expiresAt;

        VerificationData(String code, Instant expiresAt){
            System.out.println("MAIL_PASS is " + (System.getenv("MAIL_PASS") != null ? "set ✅" : "not set ❌"));
            this.code = code;
            this.expiresAt = expiresAt;
        }
    }

    private final Map<String, VerificationData> verifications = new ConcurrentHashMap<>();
    private final Random rand = new Random();

    private static final long EXPIRATION_SECONDS = 900;

    public void sendVerificationCode(String email){
        String code = String.format("%06d", rand.nextInt(1_000_000));

        verifications.put(email, new VerificationData(code, Instant.now().plusSeconds(EXPIRATION_SECONDS)));

       emailService.sendVerificationCode(email,code);
    }

    public boolean verifyCode(String email, String inputcode){
        VerificationData data = verifications.get(email);
        if(data == null){
            return false;
        }

        boolean match = data.code.equals(inputcode);
        boolean notExpired = Instant.now().isBefore(data.expiresAt);

        if(match && notExpired){
            verifications.remove(email);
            return true;
        }

        return false;
    }

    public EmailService getEmailService() {
        return emailService;
    }

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }


    
}
