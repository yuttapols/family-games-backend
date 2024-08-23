package com.it.reservation.controller;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.it.reservation.common.AbstractCommon;
import com.it.reservation.dto.response.RefreshTokenDTO;
import com.it.reservation.dto.response.TokenResDTO;
import com.it.reservation.payload.ApiResponse;
import com.it.reservation.repository.AuthenticationRepository;
import com.it.reservation.service.AuthenticationService;
import com.it.reservation.service.JwtService;
import com.it.reservation.service.RefreshTokenService;
import com.it.reservation.util.AppConstants;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = AppConstants.PROJECT_VERSION + "/authentication")
@RequiredArgsConstructor
public class AuthenticationController extends AbstractCommon{

	@Autowired
    AuthenticationService authenService;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    AuthenticationRepository authenticationRepository;

    @Autowired
    JwtService jwtService;
    
    @GetMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestParam(name = "userName") String userName, @RequestParam(name = "password") String password) throws Exception {
        ApiResponse response;
        try {
            response = getOkResponseData(authenService.login(userName, password));

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/refreshToken")
    public TokenResDTO refreshToken(@RequestParam(name = "token") String token) {

        RefreshTokenDTO refreshTokenDTO = refreshTokenService.findByToken(token);

        if (ObjectUtils.isNotEmpty(refreshTokenDTO)) {
            refreshTokenDTO = refreshTokenService.verifyExpiration(refreshTokenDTO);
            if (ObjectUtils.isNotEmpty(refreshTokenDTO)) {
                var user = authenticationRepository.findById(refreshTokenDTO.getUserId()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
                var jwt = jwtService.generateToken(user);
                return TokenResDTO.builder()
                		.userId(user.getId())
                        .accessToken(jwt)
                        .token(token).build();
            }
        } else {
            throw new RuntimeException("Refresh Token is not in DB..!!");
        }

        return TokenResDTO.builder().build();
    }
}
