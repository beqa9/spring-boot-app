package ge.softlab.spring_boot_app.services;

import ge.softlab.spring_boot_app.entities.Employee;
import ge.softlab.spring_boot_app.models.EmployeeModel;
import ge.softlab.spring_boot_app.repositories.EmployeeRepository;
import ge.softlab.spring_boot_app.repositories.PersonRepository;
import ge.softlab.spring_boot_app.repositories.PositionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepo;
    private final PersonRepository personRepo;
    private final PositionRepository positionRepo;

    public EmployeeServiceImpl(EmployeeRepository employeeRepo, PersonRepository personRepo, PositionRepository positionRepo) {
        this.employeeRepo = employeeRepo;
        this.personRepo = personRepo;
        this.positionRepo = positionRepo;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeRepo.findById(id).orElse(null);
    }

    @Override
    public Employee addEmployee(EmployeeModel model) {
        Employee employee = new Employee();
        employee.setPerson(personRepo.findById(model.personId()).orElse(null));
        employee.setPosition(positionRepo.findById(model.positionId()).orElse(null));
        employee.setHiredDate(model.hiredDate());
        return employeeRepo.save(employee);
    }

    @Override
    public Employee updateEmployee(Integer id, EmployeeModel model) {
        Employee existing = employeeRepo.findById(id).orElse(null);
        if (existing != null) {
            existing.setPerson(personRepo.findById(model.personId()).orElse(null));
            existing.setPosition(positionRepo.findById(model.positionId()).orElse(null));
            existing.setHiredDate(model.hiredDate());
            return employeeRepo.save(existing);
        }
        return null;
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeRepo.deleteById(id);
    }
}