package com.it.reservation.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.it.reservation.dto.response.RefreshTokenDTO;
import com.it.reservation.dto.response.TokenResDTO;
import com.it.reservation.entities.AuthenticationEntities;
import com.it.reservation.repository.AuthenticationRepository;
import com.it.reservation.repository.UserDetailRepository;
import com.it.reservation.service.AuthenticationService;
import com.it.reservation.service.JwtService;
import com.it.reservation.service.RefreshTokenService;
import com.it.reservation.util.Md5Util;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{
	
	@Autowired
	AuthenticationRepository authenticationRepository;
	
    @Autowired
    UserDetailRepository userDetailRepository;

    @Autowired
    ModelMapper mapper;
    
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    RefreshTokenService refreshTokenService;

	@Override
	public TokenResDTO login(String userName, String password) throws Exception {
		Optional<AuthenticationEntities> userOpt = authenticationRepository.findByUserName(userName);
        if (userOpt.isPresent()) {
        	AuthenticationEntities user = userOpt.get();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userName, passwordEncoder.encode(Md5Util.checkMd5(password))));

            var jwt = jwtService.generateToken(user);

            RefreshTokenDTO refreshTokenDTO = refreshTokenService.createRefreshToken(userName);

            return TokenResDTO.builder()
                    .userId(user.getId())
                    .accessToken(jwt)
                    .token(refreshTokenDTO.getToken())
                    .build();
        }

        return null;
	}


}
