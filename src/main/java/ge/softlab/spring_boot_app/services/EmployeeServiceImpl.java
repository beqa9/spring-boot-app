package ge.softlab.spring_boot_app.services;

import ge.softlab.spring_boot_app.entities.Employee;
import ge.softlab.spring_boot_app.models.EmployeeModel;
import ge.softlab.spring_boot_app.repositories.EmployeeRepository;
import ge.softlab.spring_boot_app.repositories.PersonRepository;
import ge.softlab.spring_boot_app.repositories.PositionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
        Optional<Employee> optionalEmployee = employeeRepo.findById(id);
        return optionalEmployee
                .filter(employee -> !employee.isDeleted())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
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
        Employee existing = employeeRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
        if (existing != null) {
            existing.setPerson(personRepo.findById(model.personId()).orElse(null));
            existing.setPosition(positionRepo.findById(model.positionId()).orElse(null));
            existing.setHiredDate(model.hiredDate());
            return employeeRepo.save(existing);
        }
        return null;
    }

    @Override
    public void deleteEmployeeById(Integer id) {
        Employee existingEmployee = employeeRepo.findById(id).orElse(null);
        if (existingEmployee != null) {
            existingEmployee.setDeleted(true); // mark as deleted
            employeeRepo.save(existingEmployee);
        }
    }
}