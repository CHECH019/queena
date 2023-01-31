package com.cdevs.queene.service.impl;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.cdevs.queene.config.Constants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    public static String extractCustomClaim(String jwt, String claim){
        return extractClaim(jwt, c -> c.get(claim, String.class));
    }

    public static String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }
    
    public static <T> T  extractClaim(String jwt, Function<Claims,T> claimsResolver) {
        final Claims claims = extractAllClaims(jwt);
        return claimsResolver.apply(claims);
    }

    public static String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public static String generateToken(
        Map<String,Object> extraClaims, 
        UserDetails userDetails
    ){
        return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis()+Constants.TOKEN_VALIDITY))
            .signWith(getSignInKey(),SignatureAlgorithm.HS256)
            .compact();
    }

    public static boolean isTokenValid(String jwt, UserDetails userDetails){
        final String userEmail = extractUsername(jwt);
        return userEmail.equals(userDetails.getUsername()) && !isTokenExpired(jwt);
    }

    private static boolean isTokenExpired(String jwt) {
        return extractExpiration(jwt).before(new Date());
    }

    private static Date extractExpiration(String jwt) {
        return extractClaim(jwt, Claims::getExpiration);
    }

    private static Claims extractAllClaims(String jwt){
        return Jwts
            .parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(jwt)
            .getBody();
    }

    private static Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(Constants.API_SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
