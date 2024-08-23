package com.it.reservation.encoder;

import org.springframework.security.crypto.password.PasswordEncoder;

public class PlainTextPasswordEncoder implements PasswordEncoder{
	@Override
	public String encode(CharSequence rawPassword) {
		// TODO Auto-generated method stub
		return rawPassword.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		// TODO Auto-generated method stub
		return rawPassword.equals(encodedPassword);
	}
	
	public static PasswordEncoder getInstance() {
		return INSTANCE;
	}
	
	public static final PasswordEncoder INSTANCE = new PlainTextPasswordEncoder();
	
	private PlainTextPasswordEncoder() {
		
	}
}
