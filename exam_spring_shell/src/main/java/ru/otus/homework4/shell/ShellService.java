package ru.otus.homework4.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.homework4.service.ApplicationRunner;

@ShellComponent
public class ShellService {
    private final ApplicationRunner applicationRunner;

    @Autowired
    public ShellService(ApplicationRunner applicationRunner) {
        this.applicationRunner = applicationRunner;
    }

    @ShellMethod(value = "Start application", key = {"start","s"})
    public void startApp() {
        applicationRunner.run();
    }
}
