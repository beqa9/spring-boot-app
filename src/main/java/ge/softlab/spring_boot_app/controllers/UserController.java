package ge.softlab.spring_boot_app.controllers;

import ge.softlab.spring_boot_app.entities.User;
import ge.softlab.spring_boot_app.models.UserModel;
import ge.softlab.spring_boot_app.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return service.getUserById(id);
    }

    @PostMapping
    public User addUser(@RequestBody UserModel model) {
        return service.addUser(model);
    }

    @PutMapping("/{id}/update")
    public User updateUser(@PathVariable Integer id, @RequestBody UserModel model) {
        return service.updateUser(id, model);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        service.deleteUserById(id);
        return ResponseEntity.ok("User with ID " + id + " deleted successfully.");
    }
}
