package com.ant.examen.config;




import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
     private static final long JWT_TOKEN_VALIDITY= 10 * 60 * 60 * 1000;
     private static final String SECRET_KEY = "ANTTechnology";
     private static final String PREFIX = "Bearer ";

    public String generateToken (Authentication authentication){
       String subject = authentication.getName();
        List<String> roles = new ArrayList<>();
        authentication.getAuthorities().forEach(auth -> roles.add(auth.getAuthority()));
        String token  = PREFIX + Jwts.builder().setSubject(subject)
                .claim("roles", roles)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY)) // in milliseconds
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
        return token;
    }


    public String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }


    public List<GrantedAuthority> getAuthorityFromToken(String token) {


        List<String> roles =  getAllClaimsFromToken(token).get("roles", List.class);


        List<GrantedAuthority> authorities = new ArrayList<>();
       roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));

            return authorities ;

    }

    private Claims getAllClaimsFromToken(String token) {

        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }







}

