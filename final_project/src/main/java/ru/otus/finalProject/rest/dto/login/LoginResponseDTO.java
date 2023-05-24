package ru.otus.finalProject.rest.dto.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class LoginResponseDTO {
    String name;
    String surname;
    String password;
    String nickname;
}
