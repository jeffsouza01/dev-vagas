package com.porto.dev_vagas.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtCandidateService {

    @Value("${security.token.secret.candidate}")
    private String secret;

    public DecodedJWT validateToken(String token){

        token = token.replace("Bearer ", "");

        Algorithm algorithm = Algorithm.HMAC256(secret);

        try {
            return JWT.require(algorithm)
                    .build()
                    .verify(token);
        } catch (JWTVerificationException err) {
            err.printStackTrace();
            return null;
        }

    }
}
