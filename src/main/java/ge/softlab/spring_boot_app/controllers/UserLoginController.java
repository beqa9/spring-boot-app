package ge.softlab.spring_boot_app.controllers;

import ge.softlab.spring_boot_app.entities.UserLogin;
import ge.softlab.spring_boot_app.repositories.UserLoginRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/logins")
@RequiredArgsConstructor
@Tag(name = "user-login-controller", description = "logins info")
public class UserLoginController {

    private final UserLoginRepository userLoginRepository;

    @GetMapping
    public List<UserLogin> getAllLogins() {
        return userLoginRepository.findAll();
    }
}