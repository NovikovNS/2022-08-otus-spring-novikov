package ru.otus.finalProject.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.finalProject.rest.dto.login.UserDto;
import ru.otus.finalProject.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class UserController {
    UserService userService;

    @GetMapping("api/user/{username}")
    public ResponseEntity<UserDto> getUserInfo(@PathVariable String username) {
        return ResponseEntity.ok().body(userService.getUserByUsername(username));
    }
}
