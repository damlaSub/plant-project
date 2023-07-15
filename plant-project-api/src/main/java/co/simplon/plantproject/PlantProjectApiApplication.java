package co.simplon.plantproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class PlantProjectApiApplication {

    public static void main(String[] args) {
	SpringApplication.run(
		PlantProjectApiApplication.class, args);
	System.out.println("Hi there! :)");
    }

}
