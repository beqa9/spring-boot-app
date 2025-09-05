package ge.softlab.spring_boot_app.models;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PersonModel(
        Integer id,
        String firstName,
        String lastName,
        LocalDate birthDate
) {
}