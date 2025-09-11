package ge.softlab.spring_boot_app.controllers;

import ge.softlab.spring_boot_app.entities.UserLogin;
import ge.softlab.spring_boot_app.repositories.UserLoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/logins")
@RequiredArgsConstructor
public class UserLoginController {

    private final UserLoginRepository userLoginRepository;

    @GetMapping
    public List<UserLogin> getAllLogins() {
        return userLoginRepository.findAll();
    }
}