package com.porto.dev_vagas.modules.candidate.useCases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.porto.dev_vagas.modules.candidate.dtos.AuthCandidateRequestDTO;
import com.porto.dev_vagas.modules.candidate.dtos.AuthCandidateResponseDTO;
import com.porto.dev_vagas.modules.candidate.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class AuthCandidateUseCase {

    @Value("${security.token.secret.candidate}")
    private String secret;

    @Autowired
    private CandidateRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authDTO) throws AuthenticationException {

        var candidate = repository.findByEmail(authDTO.email())
                .orElseThrow(() ->
                    new UsernameNotFoundException("Email or Password are incorrect"));

        var passMatch = encoder.matches(authDTO.password(), candidate.getPassword());

        if (!passMatch){
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secret);

        var token = JWT.create()
                .withIssuer("Dev_Vagas")
                .withSubject(candidate.getId().toString())
                .withClaim("roles", List.of("CANDIDATE"))
                .withExpiresAt(ExpirationTimeToken())
                .sign(algorithm);

        return AuthCandidateResponseDTO.builder()
                .access_token(token)
                .expiresTokenAt(LocalDateTime.ofInstant(ExpirationTimeToken(), ZoneOffset.systemDefault()))
                .build();
    }

    // Tempo de expiração, Instant para tempo no formato instant e Duration para adicionar o tempo
    private Instant ExpirationTimeToken(){
        return Instant.now().plus(Duration.ofMinutes(30));
    }

}
