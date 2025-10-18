package testing.demo;

//import org.springframework.shell.command.annotation.Command;
//import org.springframework.stereotype.Component;

import org.springframework.shell.standard.*;

@ShellComponent
public class TestCommands {
    @ShellMethod("Check if the shell is alive")
    public String ping() {
        return "pong";
    }

    @ShellMethod()
    public String echo(String text){
        return text;
    }
}
