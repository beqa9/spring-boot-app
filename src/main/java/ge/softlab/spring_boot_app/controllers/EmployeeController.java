package ge.softlab.spring_boot_app.controllers;

import ge.softlab.spring_boot_app.entities.Employee;
import ge.softlab.spring_boot_app.mappers.EmployeeMapper;
import ge.softlab.spring_boot_app.models.EmployeeModel;
import ge.softlab.spring_boot_app.services.EmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@Tag(name = "employee-controller", description = "crud operations")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping
    public List<EmployeeModel> getAllEmployees() {
        return employeeMapper.toModelList(employeeService.getAllEmployees());

    }

    @GetMapping("/{id}")
    public EmployeeModel getEmployeeById(@PathVariable Integer id) {
        return employeeMapper.toModel(employeeService.getEmployeeById(id));

    }

    @PostMapping
    public Employee addEmployee(@RequestBody EmployeeModel model) {
        return employeeService.addEmployee(model);
    }

    @PutMapping("/{id}/update")
    public Employee updateEmployee(@PathVariable Integer id, @RequestBody EmployeeModel model) {
        return employeeService.updateEmployee(id, model);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok("Employee with ID " + id + " deleted successfully.");
    }
}