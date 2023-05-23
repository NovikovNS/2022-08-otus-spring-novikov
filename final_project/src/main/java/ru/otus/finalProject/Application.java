package ru.otus.finalProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    // http://localhost:8080/wishlist/admin
    // http://localhost:8080/myWishList
    // http://localhost:8080/h2-console
    // http://localhost:8080/registration
    // http://localhost:8080/login
    // http://localhost:8080/actuator

}
