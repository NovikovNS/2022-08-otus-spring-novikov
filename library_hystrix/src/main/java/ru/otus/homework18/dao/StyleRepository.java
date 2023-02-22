package ru.otus.homework18.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework18.domain.Style;

import java.util.Optional;

public interface StyleRepository extends JpaRepository<Style, Long> {
    Optional<Style> findByName(String styleName);
}
