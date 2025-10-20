package testing.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;


import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.shell.ShellRunner;

import org.springframework.boot.CommandLineRunner;
@SpringBootApplication(scanBasePackages = {"testing.demo"})
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		System.out.println(System.getenv("MAIL_PASS"));
		SpringApplication.run(DemoApplication.class, args);
	}
}
