package ru.otus.finalProject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.finalProject.domain.Need;

public interface NeedRepository extends JpaRepository<Need, Long> {
}
