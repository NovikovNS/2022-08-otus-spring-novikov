package ru.otus.homework14.shell;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class ShellController {

    private final JobLauncher jobLauncher;
    private final Job migrateMongoBookLibraryToJPA;

    @SneakyThrows
    @ShellMethod(value = "migrateMongoBookLibraryToJPA", key = "sm")
    public void startMigrationFromMongoToJPA() {
        jobLauncher.run(migrateMongoBookLibraryToJPA, new JobParameters());
    }
}
