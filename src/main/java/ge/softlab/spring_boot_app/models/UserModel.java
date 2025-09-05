package ge.softlab.spring_boot_app.models;

import lombok.Builder;

@Builder
public record UserModel(
        Integer employeeId,
        String username,
        String password
) {}