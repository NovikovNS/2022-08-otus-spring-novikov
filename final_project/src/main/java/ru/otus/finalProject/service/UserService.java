package ru.otus.finalProject.service;

import ru.otus.finalProject.rest.dto.login.UserDto;

public interface UserService {

    UserDto getUserByUsername(String username);

    Boolean findByEmail(String email);

    void saveUser(UserDto user);
}
