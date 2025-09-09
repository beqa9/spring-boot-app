package ge.softlab.spring_boot_app.services;

import ge.softlab.spring_boot_app.entities.Employee;
import ge.softlab.spring_boot_app.models.EmployeeModel;

import java.util.List;

public interface EmployeeService {
    List<EmployeeModel> getAllEmployees();

    EmployeeModel getEmployeeById(Integer id);

    Employee addEmployee(EmployeeModel model);

    Employee updateEmployee(Integer id, EmployeeModel model);

    void deleteEmployeeById(Integer id);

}