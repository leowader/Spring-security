package com.jwt.authentication.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY="d57fd09cb3dda3a3ed629b6c7a84d866b2b1cc1d106a09528e507bc1cd037f86";


    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(),userDetails);
    }

    public String generateToken(Map<String,Object> extraClaims,UserDetails userDetails) {

        return Jwts.builder().setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))//24 horas
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public String getUsername(String token) {

        return getClaim(token, Claims::getSubject);
    }


    public <T> T getClaim(String token, Function<Claims,T> claimsResolve){
        final Claims claims=getAllClaims(token);
        return claimsResolve.apply(claims);
    }

    private Claims getAllClaims(String token) {

        return Jwts.parserBuilder().setSigningKey(getSigningKey())
                .build().parseClaimsJws(token).getBody();
    }

    private Key getSigningKey() {
        byte[] keyBites = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBites);
    }
    public boolean validateToken(String token, UserDetails userDetails){

        final String username = getUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

    private Date getExpiration(String token) {
        return  getClaim(token,Claims::getExpiration);
    }
}
