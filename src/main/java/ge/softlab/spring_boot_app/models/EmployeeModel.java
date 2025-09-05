package ge.softlab.spring_boot_app.models;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record EmployeeModel(
        Integer personId,
        Integer positionId,
        LocalDate hiredDate
) {}