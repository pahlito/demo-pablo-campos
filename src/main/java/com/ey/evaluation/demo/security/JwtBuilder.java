package com.ey.evaluation.demo.security;

import com.ey.evaluation.demo.model.UserJPA;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtBuilder {


    @Value("${app.secret}")
    private String secret;

    private Key key;

    @PostConstruct
    public void loadKey() {
        this.key = new SecretKeySpec(Base64.getDecoder().decode(secret),
                SignatureAlgorithm.HS256.getJcaName());
    }

    public String createToken(UserJPA user) {

        Instant now = Instant.now();
        String jwtToken = Jwts.builder()
                .claim("name", user.getUserName())
                .claim("email", user.getEmail())
                .setSubject(user.getEmail())
                .setId(String.valueOf(user.getUserId()))
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(2, ChronoUnit.HOURS)))
                .signWith(key)
                .compact();
        return jwtToken;
    }

}
