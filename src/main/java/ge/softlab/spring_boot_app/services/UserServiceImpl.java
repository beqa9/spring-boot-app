package ge.softlab.spring_boot_app.services;

import ge.softlab.spring_boot_app.entities.User;
import ge.softlab.spring_boot_app.models.UserModel;
import ge.softlab.spring_boot_app.repositories.EmployeeRepository;
import ge.softlab.spring_boot_app.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return userRepo.findAll();
    }

    @Override
    public User getUserById(Integer id) {
        return userRepo.findById(id).orElse(null);
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
        User existing = userRepo.findById(id).orElse(null);
        if (existing != null) {
            existing.setEmployee(employeeRepo.findById(model.employeeId()).orElse(null));
            existing.setUsername(model.username());
            existing.setPassword(model.password());
            return userRepo.save(existing);
        }
        return null;
    }

    @Override
    public void deleteUser(Integer id) {
        userRepo.deleteById(id);
    }
}