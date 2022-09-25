package ru.otus.homework.commandlinerunners;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.otus.homework.service.ApplicationRunner;

@Component
public class PreparationApp implements CommandLineRunner {
    private final ApplicationRunner applicationRunner;

    public PreparationApp(ApplicationRunner applicationRunner) {
        this.applicationRunner = applicationRunner;
    }

    @Override
    public void run(String... args) {
        applicationRunner.run();
    }
}