package ru.otus.homework12.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework12.domain.Style;

import java.util.Optional;

public interface StyleRepository extends JpaRepository<Style, Long> {
    Optional<Style> findByName(String styleName);
}
