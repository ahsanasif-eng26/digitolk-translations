package com.digital_tolk.translations.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

  @Value("${spring.security.jwt-secret}")
  private String secretKey;

  @Value("${spring.security.jwt-expiration}")
  private long expirationTime;

  private Key getSigningKey() {
    return Keys.hmacShaKeyFor(secretKey.getBytes());
  }

  public String generateToken(String username, Map<String, Object> claims) {
    return Jwts.builder()
        .setClaims(claims)
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
        .signWith(getSigningKey(), SignatureAlgorithm.HS512)
        .compact();
  }

  public Claims getAllClaimsFromToken(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(getSigningKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  public String extractUsername(String token) {
    return getAllClaimsFromToken(token).getSubject();
  }

  public boolean isTokenValid(String token, String username) {
    try {
      return extractUsername(token).equals(username) && !isTokenExpired(token);
    } catch (JwtException | IllegalArgumentException e) {
      return false;
    }
  }

  private boolean isTokenExpired(String token) {
    return getAllClaimsFromToken(token).getExpiration().before(new Date());
  }
}
