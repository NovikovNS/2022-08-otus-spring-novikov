package ru.otus.homework17.actuator;

import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import ru.otus.homework17.service.BookService;

@Component
@AllArgsConstructor
public class CustomHealthIndicator implements HealthIndicator {
    private final BookService bookService;

    @Override
    public Health health() {
        return Health.up().withDetail("Books count in library: ", bookService.getAllBooks().size()).build();
    }
}
