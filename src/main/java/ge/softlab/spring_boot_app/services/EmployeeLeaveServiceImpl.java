package ge.softlab.spring_boot_app.services;

import ge.softlab.spring_boot_app.entities.Employee;
import ge.softlab.spring_boot_app.entities.EmployeeLeave;
import ge.softlab.spring_boot_app.mappers.EmployeeLeaveMapper;
import ge.softlab.spring_boot_app.models.EmployeeLeaveModel;
import ge.softlab.spring_boot_app.repositories.EmployeeLeaveRepository;
import ge.softlab.spring_boot_app.repositories.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EmployeeLeaveServiceImpl implements EmployeeLeaveService {

    private final EmployeeLeaveRepository leaveRepository;
    private final EmployeeRepository employeeRepository;
    private final EmployeeLeaveMapper leaveMapper;

    public EmployeeLeaveServiceImpl(EmployeeLeaveRepository leaveRepository,
                                    EmployeeRepository employeeRepository,
                                    EmployeeLeaveMapper leaveMapper) {
        this.leaveRepository = leaveRepository;
        this.employeeRepository = employeeRepository;
        this.leaveMapper = leaveMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmployeeLeave> getAllLeaves() {
        return leaveRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public EmployeeLeave getLeaveById(Integer id) {
        return leaveRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Leave not found"));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public EmployeeLeave addLeaveByModel(EmployeeLeaveModel model) {
        Employee employee = employeeRepository.findById(model.employeeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));

        EmployeeLeave leave = new EmployeeLeave();
        leave.setEmployee(employee);
        leave.setLeaveType(model.leaveType());
        leave.setStartDate(model.startDate());
        leave.setEndDate(model.endDate());
        leave.setStatus(model.status());

        return leaveRepository.save(leave);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public EmployeeLeave updateLeaveByIdAndModel(Integer id, EmployeeLeaveModel model) {
        EmployeeLeave existingLeave = leaveRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Leave not found"));

        existingLeave.setLeaveType(model.leaveType());
        existingLeave.setStartDate(model.startDate());
        existingLeave.setEndDate(model.endDate());
        existingLeave.setStatus(model.status());

        return leaveRepository.save(existingLeave);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteLeaveById(Integer id) {
        if (!leaveRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Leave not found");
        }
        leaveRepository.deleteById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public EmployeeLeave approveLeave(Integer id) {
        EmployeeLeave leave = getLeaveById(id);
        leave.setStatus("Approved");
        return leaveRepository.save(leave);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public EmployeeLeave rejectLeave(Integer id) {
        EmployeeLeave leave = getLeaveById(id);
        leave.setStatus("Rejected");
        return leaveRepository.save(leave);
    }
}
