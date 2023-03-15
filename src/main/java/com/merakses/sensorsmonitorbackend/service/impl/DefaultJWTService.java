package com.merakses.sensorsmonitorbackend.service.impl;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.merakses.sensorsmonitorbackend.service.JWTService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;

@Service
public class DefaultJWTService implements JWTService {

    private static final String AUTHORITIES_KEY = "authorities";

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.sessionTime}")
    private long sessionTime;

    @Override
    public String generateToken(UserDetails userDetails) {
        Claims claims = createClaims(userDetails);
        return createToken(userDetails, claims);
    }

    @Override
    public String extractUsername(String token) {
        Claims claims = parseClaims(token);
        return claims.getSubject();
    }

    @Override
    public String extractAuthorities(String token) {
        Claims claims = parseClaims(token);
        return (String) claims.get(AUTHORITIES_KEY);
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .getBody();
    }

    private String createToken(UserDetails userDetails, Claims claims) {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + sessionTime))
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();
    }

    private Claims createClaims(UserDetails userDetails) {
        String commaSeparatedListOfAuthorities = userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(","));

        Claims claims = new DefaultClaims();
        claims.put(AUTHORITIES_KEY, commaSeparatedListOfAuthorities);

        return claims;
    }
}
