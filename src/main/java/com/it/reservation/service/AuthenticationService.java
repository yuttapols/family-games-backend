package com.it.reservation.service;

import com.it.reservation.dto.response.TokenResDTO;

public interface AuthenticationService {

	public TokenResDTO login(String userName, String password) throws Exception;
}
