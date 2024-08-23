package com.it.reservation.dto.response;

import lombok.Data;

@Data
public class ReasonCancelResDTO {

	private Long id;
    private String reasonNameTh;
    private String reasonNameEn;
    private String reasonDesc;
    private String status;
}
