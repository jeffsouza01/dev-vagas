package com.porto.dev_vagas.modules.company.dtos;

public record CreateJobDTO(
        String title,
        String description,
        String level,
        String benefits
) {
}
