package ge.softlab.spring_boot_app.services;


import ge.softlab.spring_boot_app.entities.EmployeeLeave;
import ge.softlab.spring_boot_app.models.EmployeeLeaveModel;

import java.util.List;

public interface EmployeeLeaveService {
    List<EmployeeLeave> getAllLeaves();

    EmployeeLeave getLeaveById(Integer id);

    EmployeeLeave addLeaveByModel(EmployeeLeaveModel model);

    EmployeeLeave updateLeaveByIdAndModel(Integer id, EmployeeLeaveModel model);

    void deleteLeaveById(Integer id);

    EmployeeLeave approveLeave(Integer id);
    EmployeeLeave rejectLeave(Integer id);
}
