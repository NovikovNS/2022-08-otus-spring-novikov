package ru.otus.finalProject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.finalProject.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String userName);

    Optional<User> findByNickname(String userNickName);

    Optional<User> findByEmail(String email);
}
