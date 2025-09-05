package ge.softlab.spring_boot_app.services;

import ge.softlab.spring_boot_app.entities.Employee;
import ge.softlab.spring_boot_app.models.EmployeeModel;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Integer id);
    Employee addEmployee(EmployeeModel model);
    Employee updateEmployee(Integer id, EmployeeModel model);
    void deleteEmployee(Integer id);
}