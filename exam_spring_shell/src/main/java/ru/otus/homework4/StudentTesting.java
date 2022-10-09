package ru.otus.homework4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.otus.homework4.config.AppConfig;

@SpringBootApplication
@EnableConfigurationProperties(AppConfig.class)
public class StudentTesting {
    public static void main(String[] args) {
        SpringApplication.run(StudentTesting.class, args);
    }
}