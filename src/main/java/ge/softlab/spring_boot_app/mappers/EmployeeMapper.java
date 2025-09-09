package ge.softlab.spring_boot_app.mappers;

import ge.softlab.spring_boot_app.entities.Employee;
import ge.softlab.spring_boot_app.models.EmployeeModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(source = "person.id", target = "personId")
    @Mapping(source = "position.id", target = "positionId")
    EmployeeModel toModel(Employee employee);

    List<EmployeeModel> toModelList(List<Employee> employees);
}
