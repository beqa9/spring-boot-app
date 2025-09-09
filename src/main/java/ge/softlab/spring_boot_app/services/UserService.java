package ge.softlab.spring_boot_app.services;


import ge.softlab.spring_boot_app.entities.User;
import ge.softlab.spring_boot_app.models.UserModel;

import java.util.List;

public interface UserService {

    List<UserModel> getAllUsers();

    UserModel getUserById(Integer id);

    User addUser(UserModel model);

    User updateUser(Integer id, UserModel model);

    void deleteUserById(Integer id);
}