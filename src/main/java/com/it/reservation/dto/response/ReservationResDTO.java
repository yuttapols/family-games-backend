package com.it.reservation.dto.response;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ReservationResDTO {
	
	private Long id;
    private Long userId;
    private String revNo;
    private Long revNoNumber;
    private Long seatTypeId;
    private String seatTypeName;
    private Timestamp revTime;
    private String revStatus;
    private String createBy;
    
    private Long reasonCancelId;
    private String reasonNameTh;
}
