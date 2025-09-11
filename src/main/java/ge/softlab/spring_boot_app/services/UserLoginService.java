package ge.softlab.spring_boot_app.services;

import ge.softlab.spring_boot_app.entities.UserLogin;
import ge.softlab.spring_boot_app.repositories.UserLoginRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {

    private final UserLoginRepository loginRepo;

    public UserLoginService(UserLoginRepository loginRepo) {
        this.loginRepo = loginRepo;
    }

    public void logLogin(Integer userId, HttpServletRequest request) {
        UserLogin log = new UserLogin();
        log.setUserId(userId);
        log.setIpAddress(request.getRemoteAddr());
        log.setUserAgent(request.getHeader("User-Agent"));
        loginRepo.save(log);
    }
}
