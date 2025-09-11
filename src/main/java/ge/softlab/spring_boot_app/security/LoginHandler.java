package ge.softlab.spring_boot_app.security;

import ge.softlab.spring_boot_app.entities.User;
import ge.softlab.spring_boot_app.entities.UserLogin;
import ge.softlab.spring_boot_app.repositories.UserLoginRepository;
import ge.softlab.spring_boot_app.services.UserLoginService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class LoginHandler implements AuthenticationSuccessHandler {
    private final UserLoginService loginService;
    private final UserLoginRepository userLoginRepository;


    public LoginHandler(UserLoginService loginService, UserLoginRepository userLoginRepository) {
        this.loginService = loginService;
        this.userLoginRepository = userLoginRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        User user = (User) authentication.getPrincipal();

        UserLogin login = new UserLogin();
        login.setUserId(user.getId());
        login.setIpAddress(request.getRemoteAddr());
        login.setUserAgent(request.getHeader("User-Agent"));
        login.setLoginTime(LocalDateTime.now());

        userLoginRepository.save(login);

        response.setStatus(HttpServletResponse.SC_OK);
    }
}

