package com.porto.dev_vagas.modules.candidate;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Candidate {

    private UUID id;
    private String name;
    private String username;
    private String email;
    private String description;
    private LocalDateTime createdAt = LocalDateTime.now();
    private String curriculum;
}
