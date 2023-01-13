package ru.otus.spring.homework15;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.spring.homework15.service.DragonService;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        DragonService dragonService = context.getBean(DragonService.class);
        dragonService.raiseDragons();
    }
}
