package ge.softlab.spring_boot_app.controllers;

import ge.softlab.spring_boot_app.entities.User;
import ge.softlab.spring_boot_app.mappers.UserMapper;
import ge.softlab.spring_boot_app.models.UserModel;
import ge.softlab.spring_boot_app.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "user-controller", description = "crud operations")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public List<UserModel> getAllUsers() {
        return userMapper.toModelList(userService.getAllUsers());

    }

    @GetMapping("/{id}")
    public UserModel getUserById(@PathVariable Integer id) {
        return userMapper.toModel(userService.getUserById(id));

    }

    @PostMapping
    public User addUser(@RequestBody UserModel model) {
        return userService.addUser(model);
    }

    @PutMapping("/{id}/update")
    public User updateUser(@PathVariable Integer id, @RequestBody UserModel model) {
        return userService.updateUser(id, model);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok("User with ID " + id + " deleted successfully.");
    }
}
