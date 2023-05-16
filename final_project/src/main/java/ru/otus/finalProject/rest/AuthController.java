package ru.otus.finalProject.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.finalProject.domain.User;
import ru.otus.finalProject.rest.dto.login.LoginResponseDTO;
import ru.otus.finalProject.rest.dto.login.RegisterUserDTO;
import ru.otus.finalProject.service.UserService;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userServiceImpl;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/api/auth/register")
    ResponseEntity<?> registerUser(@RequestBody RegisterUserDTO registerUserDTO) {
        userServiceImpl.registerUser(registerUserDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/auth/login")
    ResponseEntity<LoginResponseDTO> loginUser(@RequestParam String userName) {
        User user = userServiceImpl.getUserByUsername(userName);
        return ResponseEntity.ok().body(LoginResponseDTO.builder().name(user.getName()).password(user.getPassword()).build());
    }


}
