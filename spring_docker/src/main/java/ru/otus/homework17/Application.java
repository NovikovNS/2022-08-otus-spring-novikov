package ru.otus.homework17;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    // http://localhost:8080/library
    // http://localhost:8080/actuator
    // http://localhost:8080/actuator/health
    // http://localhost:8080/actuator/logfile
}
