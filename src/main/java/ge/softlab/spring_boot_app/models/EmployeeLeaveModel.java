package ge.softlab.spring_boot_app.models;


import lombok.Builder;

import java.time.LocalDate;

@Builder
public record EmployeeLeaveModel(
        Integer id,
        Integer employeeId,
        String leaveType,
        LocalDate startDate,
        LocalDate endDate,
        String status
) {}