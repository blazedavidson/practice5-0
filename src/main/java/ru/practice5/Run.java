package ru.practice5;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.practice5")
public class Run {
	public static void main(String[] args) {
		SpringApplication.run(Run.class, args);
	}
}
