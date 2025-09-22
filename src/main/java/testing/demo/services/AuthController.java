package testing.demo.services;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import testing.demo.Classes.*;

@RestController
@RequestMapping("/auth")

public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request){
        return authService.register(request.getEmail(), request.getPassword(), request.getName());
    }

    public String login(@RequestBody LoginRequest request){
        return authService.login(request.getEmail(),request.getPassword());
    }
}
