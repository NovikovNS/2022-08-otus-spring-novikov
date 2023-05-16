package ru.otus.finalProject.service;

import ru.otus.finalProject.rest.dto.login.LoginResponseDTO;

public interface AuthService {
    LoginResponseDTO registerUser();
}
