package ge.softlab.spring_boot_app.services;

import ge.softlab.spring_boot_app.entities.Person;
import ge.softlab.spring_boot_app.entities.User;
import ge.softlab.spring_boot_app.models.UserModel;
import ge.softlab.spring_boot_app.repositories.EmployeeRepository;
import ge.softlab.spring_boot_app.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final EmployeeRepository employeeRepo;

    public UserServiceImpl(UserRepository userRepo, EmployeeRepository employeeRepo) {
        this.userRepo = userRepo;
        this.employeeRepo = employeeRepo;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findByIsDeletedFalse();
    }

    @Override
    public User getUserById(Integer id) {
        Optional<User> optionalUser = userRepo.findById(id);
        return optionalUser
                .filter(user -> !user.isDeleted())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @Override
    public User addUser(UserModel model) {
        User user = new User();
        user.setEmployee(employeeRepo.findById(model.employeeId()).orElse(null));
        user.setUsername(model.username());
        user.setPassword(model.password());
        return userRepo.save(user);
    }

    @Override
    public User updateUser(Integer id, UserModel model) {
        User existing = userRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        if (existing != null) {
            existing.setEmployee(employeeRepo.findById(model.employeeId()).orElse(null));
            existing.setUsername(model.username());
            existing.setPassword(model.password());
            return userRepo.save(existing);
        }
        return null;
    }

    @Override
    public void deleteUserById(Integer id) {
        User existingUser = userRepo.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setDeleted(true); // mark as deleted
            userRepo.save(existingUser);
        }
    }
}