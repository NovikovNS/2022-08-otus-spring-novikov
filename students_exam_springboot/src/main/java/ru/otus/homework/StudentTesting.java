package ru.otus.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.otus.homework.config.AppConfig;

@SpringBootApplication
@EnableConfigurationProperties(AppConfig.class)
public class StudentTesting {
    public static void main(String[] args) {
        SpringApplication.run(StudentTesting.class, args);
    }
}