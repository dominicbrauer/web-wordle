package dev.dominicbrauer.web_wordle_tim24;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebWordleTim24Application {

	public static void main(String[] args) {
    Date current = new Date();
    System.out.println(current.getTime());

		SpringApplication.run(WebWordleTim24Application.class, args);
	}

}
