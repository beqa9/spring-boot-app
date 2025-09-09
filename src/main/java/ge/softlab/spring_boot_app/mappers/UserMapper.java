package ge.softlab.spring_boot_app.mappers;

import ge.softlab.spring_boot_app.entities.User;
import ge.softlab.spring_boot_app.models.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "employee.id", target = "employeeId")
    UserModel toModel(User user);

    List<UserModel> toModelList(List<User> users);
}
