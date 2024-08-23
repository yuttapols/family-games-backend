package com.it.reservation.service.impl;

import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.it.reservation.repository.AuthenticationRepository;
import com.it.reservation.service.JwtService;
import com.it.reservation.service.RefreshTokenService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService{

	private String jwtSigningKey = "413F4428472B4B6250655368566D5970337336763979244226452948404D6351";
	
	public static final long JWT_TOKEN_VALIDITY =  15 * 60; // 1 MIN Token Expire
	
    @Autowired
    RefreshTokenService refreshTokenService;
    
    @Autowired
    AuthenticationRepository authenticationRepository;


	@Override
	public String extractUserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	@Override
	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(), userDetails);
	}

	@Override
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String userName = extractUserName(token);
		return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
		Claims  claims = extractAllClaims(token);
		return claimsResolvers.apply(claims);
	}

	private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
		return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000 ))
				.signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());	
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	private Claims extractAllClaims(String token) {
		
		return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
	}

	private Key getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	
	public Authentication getAuthentication(String token) {
		Claims claims = Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();

        Collection<? extends GrantedAuthority> authorities = new ArrayList<>();
        User principal = new User(claims.getSubject(), token, authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }
	
	@Override
	public Boolean validate(final String tokenAsString) {
	    try {
	    	
	    	Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(tokenAsString).getBody();
	    	return true;
	    } catch (Exception e) {
	    	return false;
	    }
	}
}
