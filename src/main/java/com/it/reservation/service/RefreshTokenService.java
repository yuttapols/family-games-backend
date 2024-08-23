package com.it.reservation.service;

import com.it.reservation.dto.response.RefreshTokenDTO;

public interface RefreshTokenService {
	public RefreshTokenDTO findByToken(String token) ;
	public RefreshTokenDTO createRefreshToken(String userName);
	public RefreshTokenDTO verifyExpiration(RefreshTokenDTO token) ;
}
