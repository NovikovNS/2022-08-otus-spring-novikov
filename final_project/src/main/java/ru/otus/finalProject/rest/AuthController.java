package ru.otus.finalProject.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.finalProject.rest.dto.login.LoginResponseDTO;
import ru.otus.finalProject.rest.dto.login.UserDto;
import ru.otus.finalProject.service.UserService;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userServiceImpl;

    @PostMapping("/api/auth/register")
    ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        userServiceImpl.saveUser(userDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/auth/login")
    ResponseEntity<LoginResponseDTO> loginUser(@RequestParam String userName) {
        UserDto user = userServiceImpl.getUserByUsername(userName);
        return ResponseEntity.ok().body(LoginResponseDTO.builder()
                .name(user.getUsername()).password(user.getPassword())
                .build());
    }


}
