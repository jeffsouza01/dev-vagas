package com.porto.dev_vagas.modules.candidate.controllers;

import com.porto.dev_vagas.modules.candidate.Candidate;
import com.porto.dev_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import com.porto.dev_vagas.modules.candidate.useCases.ListAllJobsByFilterUseCase;
import com.porto.dev_vagas.modules.candidate.useCases.ProfileCandidateUseCase;
import com.porto.dev_vagas.modules.company.entities.Job;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase candidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileUseCase;

    @Autowired
    private ListAllJobsByFilterUseCase allJobsByFilter;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody Candidate candidate) {

        try {
            var result = this.candidateUseCase.execute(candidate);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @GetMapping("/")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> profile(HttpServletRequest request){

        var idCandidate = request.getAttribute("candidate_id");

        try {
            var profile = profileUseCase.execute(UUID.fromString(idCandidate.toString()));
            return ResponseEntity.status(HttpStatus.CREATED).body(profile);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }


    @GetMapping("/jobs")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Tag(name = "Vagas", description = "Informações para candidatos")
    @Operation(summary = "Listagem de vagas disponíveis para o candidato",
    description = "Função resposável para listar todas as vagas disponiveis, beaseada no filter")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(array = @ArraySchema(schema =  @Schema(implementation = Job.class)))
            })
    })
    public ResponseEntity<Object> jobs(@RequestParam String filter){

        try {
            var jobs = allJobsByFilter.execute(filter);
            return ResponseEntity.status(HttpStatus.CREATED).body(jobs);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }
}
