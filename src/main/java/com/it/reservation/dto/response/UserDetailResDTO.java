package com.it.reservation.dto.response;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class UserDetailResDTO {

	private Long id;
    private Long userId;
    private String customerNo;
    private String fristName;
    private String middleName;
    private String lastName;
    private String nickName;
    private String email;
    private String telephone;
    private String status;
    private String createBy;
    private Timestamp createDate;
    private String updateBy;
    private Timestamp updateDate;
}
