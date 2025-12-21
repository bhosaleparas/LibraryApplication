package com.example.LibraryApplication.security;


import com.example.LibraryApplication.entity.UserName;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class AuthUtil {

    @Value("${jwt.secret}")
    private String jwtSecretKey;

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateaccesToken(UserName userName){
        return Jwts.builder()
                .subject(userName.getUsername())
                .claim("userId",userName.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() +1000*60*10))
                .signWith(getSecretKey())
                .compact();
    }



    public String getUserNameFromToken(String token) {
        Claims claim= Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claim.getSubject();
    }
}
