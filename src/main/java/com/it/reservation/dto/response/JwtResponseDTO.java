package com.it.reservation.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponseDTO {
	private String accessToken;
	private String token;
}
