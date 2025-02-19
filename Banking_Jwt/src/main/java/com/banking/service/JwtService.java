package com.banking.service;

import java.security.SignatureException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	private final Long expTime = TimeUnit.HOURS.toMillis(24);
	private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	public String generateToken(String username, List<String> roles) {
	    return Jwts.builder()
	        .setSubject(username)
	        .claim("roles", roles)
	        .setId(UUID.randomUUID().toString())
	        .setIssuedAt(new Date())
	        .setExpiration(new Date(System.currentTimeMillis() + expTime))
	        .signWith(key, SignatureAlgorithm.HS256)
	        .compact();
	}

	public Claims validateToken(String token) {
	    return Jwts.parserBuilder()
	        .setSigningKey(key)
	        .build()
	        .parseClaimsJws(token)
	        .getBody();
	}

	public boolean isTokenValid(String token) {
	    try {
	        validateToken(token);
	        return true;
	    } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | 
	             io.jsonwebtoken.security.SignatureException | IllegalArgumentException e) {
	       // Log.error("Token validation failed: {}", e.getMessage());
	        return false;
	    }
	}
}
