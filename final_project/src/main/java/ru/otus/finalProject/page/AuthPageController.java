package ru.otus.finalProject.page;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.finalProject.rest.dto.login.UserDto;
import ru.otus.finalProject.service.UserService;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class AuthPageController {
    private final UserService userService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/registration/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model){
        Boolean isUserExist = userService.findByEmail(user.getEmail());
        if (isUserExist) {
            result.rejectValue("email", null, "Account with that email is already registered ");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "registration";
        }
        userService.saveUser(user);
        return "redirect:/mywishlist";
    }
}
