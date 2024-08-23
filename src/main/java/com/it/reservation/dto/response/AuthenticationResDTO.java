package com.it.reservation.dto.response;

import java.sql.Timestamp;

import com.it.reservation.entities.RoleEntities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResDTO {

	private Long id;
	private String userName;
	private String password;
	private String status;
	private String createBy;
	private Timestamp createDate;
	private String updateBy;
	private Timestamp updateDate;
	private RoleEntities role;
    
    //user
    private UserDetailResDTO userDetail;
}
