package com.it.reservation.dto.request;

import lombok.Data;

@Data
public class ReservationReqDTO {
	private Long userId;
	private Long seatTypeId;
	private Long reasonCancelId;
	private String revStatus;
}
