package ru.otus.finalProject.rest.dto.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class RegisterUserDTO {
    String name;
    String surname;
    String password;
    String nickname;
}
