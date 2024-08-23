package com.it.reservation.payload;

import lombok.Data;

@Data
public class CustomerUserAttr {
	private String customerNo;
	private String customerName;
	private Long roleId;
	private String roleName;
	private String ipAddress;
}
