package ru.otus.finalProject.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.otus.finalProject.dao.UserRepository;
import ru.otus.finalProject.rest.dto.converter.UserDtoConverter;
import ru.otus.finalProject.rest.dto.login.UserDto;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    UserDtoConverter userDtoConverter;

    @Override
    public UserDto getUserByUsername(String username) {
        return userDtoConverter.mapToDto(userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found")));
    }

    @Override
    public Boolean findByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public void saveUser(UserDto userDto) {
        userRepository.save(userDtoConverter.mapToEntity(userDto));
    }
}
