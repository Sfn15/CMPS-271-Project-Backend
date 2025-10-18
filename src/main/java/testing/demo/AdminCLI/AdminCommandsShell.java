package testing.demo.AdminCLI;
//import org.springframework.shell.command.annotation.Command;
//import org.springframework.shell.command.annotation.Option;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import jakarta.transaction.Transactional;
//import org.springframework.stereotype.Component;
import testing.demo.Classes.*;
import testing.demo.repository.*;
import testing.demo.services.*;

@ShellComponent
//@Command(command = "admin", description = "Admin commands to manage users")
public class AdminCommandsShell {
    private final AdminCommands adminCommands;

    public AdminCommandsShell(UserRepository userRepo, AuthService authService){
        this.adminCommands = new AdminCommands(userRepo, authService);
    }


    @Transactional
    //@Command(command = "finduser", description = "Find a user by email or UUID")
    @ShellMethod(key = "finduser", value = "Find a user by email or UUID")
    public String findUserCommand(@ShellOption(help = "Email or UUID") String key, @ShellOption(help = "0 = email,1 = UUID") int option){
        User user = adminCommands.findUser(key, option);
        if(user ==  null){
            return "User not found!";
        }

        return adminCommands.userInfo(user);
    }

    @Transactional
    @ShellMethod(key = "deleteuser", value = "Delete a user by email or UUID")
    public String deleteuser(@ShellOption(help = "Email or UUID of the user") String key, @ShellOption(help = "0 = email, 1 = UUID") int option){
        return adminCommands.deleteUser(key, option);
    }
}
