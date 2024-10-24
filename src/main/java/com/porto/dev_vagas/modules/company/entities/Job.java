package com.porto.dev_vagas.modules.company.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "tb_job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String description;

    @NotBlank(message = "Campo obrigatório")
    private String level;
    private String benefits;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "id_company", insertable = false, updatable = false)
    private Company company;

    @Column(name = "id_company", nullable = false)
    private UUID idCompany;
}
