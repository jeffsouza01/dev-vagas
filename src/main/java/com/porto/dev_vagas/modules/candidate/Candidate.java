package com.porto.dev_vagas.modules.candidate;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "tb_candidate")
@Data
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;

    @NotBlank
    // Regex para validação que não tenha espaço
    @Pattern(regexp = "\\S+", message = "O Campo [username] não deve conter espaço")
    private String username;

    @Email(message = "O Campo [email] deve conter um email válido")
    private String email;

    @Length(min = 3)
    private String password;

    @Length(max = 150)
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;
    private String curriculum;
}
