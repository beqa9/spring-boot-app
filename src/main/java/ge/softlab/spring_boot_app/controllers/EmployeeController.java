package ge.softlab.spring_boot_app.controllers;

import ge.softlab.spring_boot_app.entities.Employee;
import ge.softlab.spring_boot_app.models.EmployeeModel;
import ge.softlab.spring_boot_app.services.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) {
        return service.getEmployeeById(id);
    }

    @PostMapping
    public Employee addEmployee(@RequestBody EmployeeModel model) {
        return service.addEmployee(model);
    }

    @PutMapping("/{id}/update")
    public Employee updateEmployee(@PathVariable Integer id, @RequestBody EmployeeModel model) {
        return service.updateEmployee(id, model);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) {
        service.deleteEmployeeById(id);
        return ResponseEntity.ok("Employee with ID " + id + " deleted successfully.");
    }
}