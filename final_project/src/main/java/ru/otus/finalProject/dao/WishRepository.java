package ru.otus.finalProject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.finalProject.domain.Wish;

import java.util.List;

public interface WishRepository extends JpaRepository<Wish, Long> {
    List<Wish> findByUserId(Long userId);
}
