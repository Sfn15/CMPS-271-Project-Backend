package testing.demo.AdminCLI;
import java.util.*;
import testing.demo.repository.UserRepository;
import testing.demo.Classes.*;
import testing.demo.services.*;

public class AdminCommands{
    private final UserRepository userRepo;
    private final AuthService authService;
    public AdminCommands(UserRepository userRepo, AuthService authService){
        this.userRepo = userRepo;
        this.authService = authService;
    }

    
    public User findUser(String key, int option){
        
        switch (option) {
            case 0: //find by email
            return userRepo.findByEmail(key);

            case 1: //find by id;
            UUID id;
            try {
                id = UUID.fromString(key);
            } catch (IllegalArgumentException e){
                System.out.println("Invalid UUID format!\n");
                return null;
            }
            return userRepo.findById(id).orElse(null);
        
            default:
            System.out.println("invalid option!\n");
            return null;
        }
    }

    public String deleteUser(String key, int option){
        User user = findUser(key, option);
        if(user == null){
            return "User not found";
        } else {
            String s = "User" + userInfo(user) +"deleted successfully";
            userRepo.delete(user);
            return s;
        }
    }

    public String addUser(String email, String password, String name){
        return authService.register(email, password, name);
    }

    public String userInfo(User user){
        return String.format("Username: %s\nEmail: %s\nUUID: %s\n", user.getName(),user.getEmail(), user.getId().toString());
    }
}