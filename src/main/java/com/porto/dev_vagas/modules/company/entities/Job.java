package com.porto.dev_vagas.modules.company.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "tb_jobs")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String title;
    private String description;

    @NotBlank(message = "Campo obrigatório")
    private String level;
    private String benefits;


    @ManyToOne
    @JoinColumn(name = "id_company", insertable = false, updatable = false)
    private Company company;

    @Column(name = "id_company", nullable = false)
    private UUID idCompany;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
