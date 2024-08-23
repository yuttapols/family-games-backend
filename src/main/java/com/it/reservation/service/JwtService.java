package com.it.reservation.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

	String extractUserName(String token);

	String generateToken(UserDetails userDetails);

	boolean isTokenValid(String token, UserDetails userDetails);
	
	public Authentication getAuthentication(String token);
	
	public Boolean validate(String tokenAsString);
}
