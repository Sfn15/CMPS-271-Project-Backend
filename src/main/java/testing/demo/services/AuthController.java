package testing.demo.services;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import testing.demo.Classes.*;
import testing.demo.services.EmailVerificationService;

@RestController
@RequestMapping("/auth")

public class AuthController {
    private final AuthService authService;
    private final EmailVerificationService verificationService;

    private final Map<String, RegisterRequest> pendingRegistrations = new ConcurrentHashMap<>();
    public AuthController(AuthService authService, EmailVerificationService verificationService){
        this.authService = authService;
        this.verificationService = verificationService;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request){

        pendingRegistrations.put(request.getEmail(), request);

        verificationService.sendVerificationCode(request.getEmail());

        return "Verification code sent to " + request.getEmail();
    }

    @PostMapping("/verify")
    public String verify(@RequestParam String email, @RequestParam String code){
        boolean ok = verificationService.verifyCode(email, code);

        if(!ok){
            return "Invalid or expired code";
        }

        RegisterRequest request = pendingRegistrations.remove(email);
        if(request == null){
            return "No pending registration found for this email";
        }

        return authService.register(request.getEmail(), request.getPassword(), request.getName());
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request){
        return authService.login(request.getEmail(),request.getPassword());
    }

    @PostMapping("/test-email")
    public String testEmail(@RequestParam String email){

        if(verificationService == null){
            return "Verification service is null";
        }

        if(verificationService.getEmailService() == null){
            return "mail service is null";
        }

        try{
            verificationService.sendVerificationCode(email);
            return "Code sent successfully";
        } catch (Exception e){
            e.printStackTrace();
            return "Email failed: " + e.getMessage();
        }
    }

    @GetMapping("/ping")
    public String ping(){
        System.out.println("Console test");
        return "pong";
    }

}
