package com.porto.dev_vagas.modules.company.useCases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.porto.dev_vagas.modules.company.dtos.AuthCompanyDTO;
import com.porto.dev_vagas.modules.company.repositories.CompanyRepository;
import com.porto.dev_vagas.providers.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;

@Service
public class AuthCompanyUseCase {

    @Autowired
    private CompanyRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Value("${security.token.secret}")
    private String secret;

    @Autowired
    private JWTService jwtService;

    public String execute(AuthCompanyDTO authDTO) throws AuthenticationException {
        var company = repository.findByEmail(authDTO.email()).orElseThrow(
                () -> new UsernameNotFoundException("Email / Password not found"));

        var passMatches = encoder.matches(authDTO.password(), company.getPassword());

        if (!passMatches) {
            throw new AuthenticationException();
        }

        //Geração Token
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create().withIssuer("dev_vagas")
                .withExpiresAt(ExpirationTimeToken())
                .withSubject(company.getId().toString())
                .sign(algorithm);
    }


    // Tempo de expiração, Instant para tempo no formato instant e Duration para adicionar 2h
    private Instant ExpirationTimeToken(){
        return Instant.now().plus(Duration.ofHours(2));
    }
}
