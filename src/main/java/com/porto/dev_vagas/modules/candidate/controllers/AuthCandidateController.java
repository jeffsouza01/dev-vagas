package com.porto.dev_vagas.modules.candidate.controllers;

import com.porto.dev_vagas.modules.candidate.dtos.AuthCandidateRequestDTO;
import com.porto.dev_vagas.modules.candidate.dtos.AuthCandidateResponseDTO;
import com.porto.dev_vagas.modules.candidate.useCases.AuthCandidateUseCase;
import com.porto.dev_vagas.modules.company.entities.Job;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/auth")
public class AuthCandidateController {

    @Autowired
    private AuthCandidateUseCase authUseCase;


    @PostMapping("/candidate")
    @Tag(name = "Autenticação de Candidato", description = "Autenticar um candidato")
    @Operation(summary = "Autenticaçãop de candidato",
            description = "Função responsável para criação de uma nova vaga")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(array = @ArraySchema(schema =  @Schema(implementation = AuthCandidateResponseDTO.class)))
            })
    })
    public ResponseEntity<Object> auth(@RequestBody AuthCandidateRequestDTO authDTO) throws AuthenticationException {

        try {
            var authCandidate = authUseCase.execute(authDTO);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(authCandidate);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
