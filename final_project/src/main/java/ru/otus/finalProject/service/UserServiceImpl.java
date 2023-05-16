package ru.otus.finalProject.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.otus.finalProject.dao.UserRepository;
import ru.otus.finalProject.domain.User;
import ru.otus.finalProject.rest.dto.login.RegisterUserDTO;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public void registerUser(RegisterUserDTO registerUserDTO) {
        User user = User.builder()
                .name(registerUserDTO.getName())
                .surname(registerUserDTO.getSurname())
                .password(passwordEncoder.encode(registerUserDTO.getPassword()))
                .build();
        userRepository.save(user);
    }
}
