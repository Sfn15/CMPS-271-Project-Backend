package testing.demo.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import testing.demo.Classes.User;
import testing.demo.Classes.exceptions.EmailAlreadyExistsException;
import testing.demo.Classes.exceptions.UserNotFoundException;
import testing.demo.Classes.exceptions.WrongPasswordException;
import testing.demo.repository.UserRepository;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(String email, String rawPassword, String name){
        if(userRepository.findByEmail(email) != null){
            throw new EmailAlreadyExistsException("Email already exists: " + email);
        }

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(passwordEncoder.encode(rawPassword));
        userRepository.save(user);
        
        return "User registered successfully";
    }

    public String login(String email, String rawPassword){
        User user = userRepository.findByEmail(email);

        if(user == null){
            throw new UserNotFoundException("No user found with email address:" + email);
        }

        if(passwordEncoder.matches(rawPassword, user.getPassword())){
            return "Welcome back, " + user.getName() + "!";
        } else {
            throw new WrongPasswordException("Invalid password!");
        }

    }
}
