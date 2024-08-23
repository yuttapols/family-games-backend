package com.it.reservation.dto.response;

import lombok.Data;

@Data
public class SeatTypeResDTO {
	
	private Long id;
    private String seatTypeNameTh;
    private String seatTypeNameEn;
    private String seatTypeUnit;
    private String status;
}
