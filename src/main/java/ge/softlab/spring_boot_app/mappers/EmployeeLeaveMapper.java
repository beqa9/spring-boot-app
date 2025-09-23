package ge.softlab.spring_boot_app.mappers;


import ge.softlab.spring_boot_app.entities.EmployeeLeave;
import ge.softlab.spring_boot_app.models.EmployeeLeaveModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeLeaveMapper {

    @Mapping(source = "employee.id", target = "employeeId")
    EmployeeLeaveModel toModel(EmployeeLeave leave);

    List<EmployeeLeaveModel> toModelList(List<EmployeeLeave> leaves);
}
