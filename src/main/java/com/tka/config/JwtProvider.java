package com.tka.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtProvider {

    private static SecretKey key = Keys.hmacShaKeyFor(JWTConstant.SECRET_KEY.getBytes());

    // Generate JWT
    public static String generateToken(Authentication auth) {
        String jwt = Jwts.builder()
                .setIssuer("codewithzosh")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hrs
                .claim("email", auth.getName())
                .signWith(key)
                .compact();
        return jwt;
    }

    // ✅ Parse JWT
    public static String getEmailFromJwtToken(String jwt) {
        // Remove "Bearer " prefix
        if (jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7);
        }

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)  // ✅ for signed tokens
                .getBody();

        return claims.get("email", String.class);
    }
}
