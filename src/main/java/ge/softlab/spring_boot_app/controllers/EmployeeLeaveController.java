package ge.softlab.spring_boot_app.controllers;

import ge.softlab.spring_boot_app.entities.EmployeeLeave;
import ge.softlab.spring_boot_app.mappers.EmployeeLeaveMapper;
import ge.softlab.spring_boot_app.models.EmployeeLeaveModel;
import ge.softlab.spring_boot_app.services.EmployeeLeaveService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leaves")
@Tag(name = "employee-leave-controller", description = "CRUD operations for employee leaves")
public class EmployeeLeaveController {

    private final EmployeeLeaveService leaveService;
    private final EmployeeLeaveMapper leaveMapper;

    public EmployeeLeaveController(EmployeeLeaveService leaveService, EmployeeLeaveMapper leaveMapper) {
        this.leaveService = leaveService;
        this.leaveMapper = leaveMapper;
    }

    @GetMapping
    public List<EmployeeLeaveModel> getAllLeaves() {
        return leaveMapper.toModelList(leaveService.getAllLeaves());
    }

    @GetMapping("/{id}")
    public EmployeeLeaveModel getLeaveById(@PathVariable Integer id) {
        return leaveMapper.toModel(leaveService.getLeaveById(id));
    }

    @PostMapping
    public EmployeeLeave addLeave(@RequestBody EmployeeLeaveModel model) {
        return leaveService.addLeaveByModel(model);
    }

    @PutMapping("/{id}/update")
    public EmployeeLeave updateLeave(@PathVariable Integer id, @RequestBody EmployeeLeaveModel model) {
        return leaveService.updateLeaveByIdAndModel(id, model);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteLeave(@PathVariable Integer id) {
        leaveService.deleteLeaveById(id);
        return ResponseEntity.ok("Leave with ID " + id + " deleted successfully.");
    }

    @PutMapping("/{id}/approve")
    public EmployeeLeave approveLeave(@PathVariable Integer id) {
        return leaveService.approveLeave(id);
    }

    @PutMapping("/{id}/reject")
    public EmployeeLeave rejectLeave(@PathVariable Integer id) {
        return leaveService.rejectLeave(id);
    }
}