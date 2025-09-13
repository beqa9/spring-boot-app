package ge.softlab.spring_boot_app.services;

import ge.softlab.spring_boot_app.entities.Person;
import ge.softlab.spring_boot_app.entities.User;
import ge.softlab.spring_boot_app.mappers.UserMapper;
import ge.softlab.spring_boot_app.models.UserModel;
import ge.softlab.spring_boot_app.repositories.EmployeeRepository;
import ge.softlab.spring_boot_app.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final EmployeeRepository employeeRepo;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepo, EmployeeRepository employeeRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.employeeRepo = employeeRepo;
        this.userMapper = userMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserModel> getAllUsers() {
        return userMapper.toModelList(userRepo.findByIsDeletedFalse());
    }

    @Transactional(readOnly = true)
    @Override
    public UserModel getUserById(Integer id) {
        User user = userRepo.findById(id)
                .filter(u -> !u.isDeleted())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return userMapper.toModel(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public User addUser(UserModel model) {
        User user = new User();
        user.setEmployee(employeeRepo.findById(model.employeeId()).orElse(null));
        user.setUsername(model.username());
        user.setPassword(model.password());
        return userRepo.save(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
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

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteUserById(Integer id) {
        User existingUser = userRepo.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setDeleted(true); // mark as deleted
            userRepo.save(existingUser);
        }
    }
}