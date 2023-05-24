package ru.otus.finalProject.rest.dto.converter;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.otus.finalProject.domain.User;
import ru.otus.finalProject.rest.dto.login.UserDto;

@Service
@AllArgsConstructor
public class UserDtoConverter implements DtoConverter<User, UserDto>{

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto mapToDto(User entity) {
        return UserDto.builder()
                .username(entity.getUsername())
                .lastname(entity.getLastName())
                .wishes(entity.getWishes())
                .build();
    }

    @Override
    public User mapToEntity(UserDto dto) {
        return User.builder()
                .username(dto.getUsername())
                .lastName(dto.getLastname())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();
    }
}
