package ge.softlab.spring_boot_app.models;

import lombok.Builder;

@Builder
public record PositionModel(
        String name
) {}