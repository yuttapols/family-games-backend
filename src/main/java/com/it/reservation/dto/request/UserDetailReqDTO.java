package com.it.reservation.dto.request;

import lombok.Data;

@Data
public class UserDetailReqDTO {

    private String userName;
    private String password;
    private String fristName;
    private String lastName;
    private String email;
    private String telephone;
}
