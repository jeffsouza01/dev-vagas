package com.porto.dev_vagas.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class JWTService {

    @Value("${security.token.secret}")
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

//    public void genarateToken(){}


    // Tempo de expiração, Instant para tempo no formato instant e Duration para adicionar 2h
    private Instant ExpirationTimeToken(){
        return Instant.now().plus(Duration.ofHours(2));
    }

}
