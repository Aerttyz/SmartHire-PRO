package com.smarthirepro.core.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.smarthirepro.domain.model.Empresa;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

    private String jwtSecret = "bxOksa8BHgdAhR80Y3pEYvS5M+MnF2sheFDqprkTqQ4odqoszJLW1ikw64/nT/dTvlgrcBTq7HfK1B9Gai2h5A==";

    private int jwtExpirationMs = 900000;

    public String generateTokenFromEmpresa(Empresa empresa) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("id", empresa.getId());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject((empresa.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }

    public Key getSigningKey() {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        return key;
    }

    public String getEmailToken(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String authToken) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(authToken)
                .getBody();
        return true;
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build().parseClaimsJws(token)
                .getBody();
    }

    public UUID getIdFromToken(String token) {
        Claims claims = getClaims(token);
        String idString = claims.get("empresaId", String.class);
        return UUID.fromString(idString);
    }
}