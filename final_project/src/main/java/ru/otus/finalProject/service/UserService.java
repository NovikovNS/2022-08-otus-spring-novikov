package ru.otus.finalProject.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.otus.finalProject.domain.User;
import ru.otus.finalProject.rest.dto.login.RegisterUserDTO;

public interface UserService extends UserDetailsService {

    User getUserByUsername(String username);
    void registerUser(RegisterUserDTO registerUserDTO);
}
