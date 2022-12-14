package ru.otus.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.homework.config.AppConfig;
import ru.otus.homework.service.ApplicationRunner;

@SpringBootApplication
@EnableConfigurationProperties(AppConfig.class)
public class StudentTesting {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(StudentTesting.class, args);
        ApplicationRunner applicationRunner = context.getBean(ApplicationRunner.class);
        applicationRunner.run();
    }
}