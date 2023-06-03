package edu.bilkent.bilbilet.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;

@Component
public class JWTUtils {
    private String accessSignKey = "broccoliisthegreatestfoodinthewholeuniverse";
    private String refreshSignKey = "pizzaisalsogoodbutnotreallygoodwithoutbroccoli";
    private long accessExp = TimeUnit.HOURS.toMillis(10);
    private long refreshExp = TimeUnit.HOURS.toMillis(24);

    private Claims extractAllClaims(String token, String signKey)  {
        SecretKey key = Keys.hmacShaKeyFor(signKey.getBytes(StandardCharsets.UTF_8));

        return Jwts.parserBuilder()
            .setSigningKey(key)   
            .build()
            .parseClaimsJws(token)
            .getBody();

            // Jwt<?,?> jwt;

            // try {
            //     jwt = Jwts.parserBuilder() // (1)
                    
            //     .verifyWith(key)         //     or a static key used to verify all signed JWTs
            //     //.decryptWith(key)        //     or a static key used to decrypt all encrypted JWTs
                    
            //     .build()                   // (3)
                    
            //     .parseClaimsJws(token);           // (4) or parseClaimsJws, parseClaimsJwe, parseContentJws, etc
                
            //     // we can safely trust the JWT
                 
            // catch (JwtException ex) {      // (5)
                
            //     // we *cannot* use the JWT as intended by its creator
            // }
    }

    public Date extractAccessExpiration(String token) {
        return extractAllClaims(token, accessSignKey).getExpiration();
    }

    public String extractAccessUsername(String token) {
        return extractAllClaims(token, accessSignKey).getSubject();
    }

    public boolean isAccessTokenExpired(String token) {
        return extractAccessExpiration(token).before(new Date());
    }

    public Date extractRefreshExpiration(String token) {
        return extractAllClaims(token, refreshSignKey).getExpiration();
    }

    public String extractRefreshUsername(String token) {
        return extractAllClaims(token, refreshSignKey).getSubject();
    }

    public boolean isRefreshTokenExpired(String token) {
        return extractRefreshExpiration(token).before(new Date());
    }

    private String createToken(Map<String, Object> claims, UserDetails user, String signKey, long expiration) {
        long issuedAt = System.currentTimeMillis();
        SecretKey key = Keys.hmacShaKeyFor(signKey.getBytes(StandardCharsets.UTF_8));

        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .claim("authorities", user.getAuthorities())
                .setIssuedAt(new Date(issuedAt))
                .setExpiration(new Date(issuedAt + expiration))
                .signWith(key);

        return builder.compact();
    }

    private String createToken(UserDetails user, String signKey, long expiration) {
        long issuedAt = System.currentTimeMillis();
        SecretKey key = Keys.hmacShaKeyFor(signKey.getBytes(StandardCharsets.UTF_8));
        Map<String, Object> claims = new HashMap<>();

        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .claim("authorities", user.getAuthorities())
                .setIssuedAt(new Date(issuedAt))
                .setExpiration(new Date(issuedAt + expiration))
                .signWith(key);

        return builder.compact();
    }

    public String createAccessToken(Map<String, Object> claims, UserDetails user) {
        return createToken(claims, user, accessSignKey, accessExp);
    }

    public String createRefreshToken(Map<String, Object> claims, UserDetails user) {
        return createToken(claims, user, refreshSignKey, refreshExp);
    }

    public String createAccessToken(UserDetails user) {
        return createToken(user, accessSignKey, accessExp);
    }

    public String createRefreshToken(UserDetails user) {
        return createToken(user, refreshSignKey, refreshExp);
    }

    public boolean validateAccessToken(String token, UserDetails user) {
        String username = extractAccessUsername(token);
        return user.getUsername().equals(username) && !isAccessTokenExpired(token);
    }

    public boolean validateRefreshToken(String token, UserDetails user) {
        String username = extractRefreshUsername(token);
        return user.getUsername().equals(username) && !isRefreshTokenExpired(token);
    }
    // public boolean validateRefreshToken(String token) {
    //     try {
    //         Jwts.parser().setSigningKey(refreshSignKey).parseClaimsJws(token);
    //         return true;
    //     } catch (SignatureException | MalformedJwtException | ExpiredJwtException |
    //             UnsupportedJwtException | IllegalArgumentException e) {
    //         System.out.println("Invalid JWT token");
    //         throw e;
    //     }
    // }
}
