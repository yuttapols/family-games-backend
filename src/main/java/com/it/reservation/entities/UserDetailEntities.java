package com.it.reservation.entities;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
@Table(name = "user_detail")
public class UserDetailEntities {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id", length = 11)
    private Long userId;

    @Column(name = "customer_no", length = 50)
    private String customerNo;

    @Column(name = "frist_name", length = 100)
    private String fristName;

    @Column(name = "middle_name", length = 20)
    private String middleName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(name = "nick_name", length = 50)
    private String nickName;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "telephone", length = 15)
    private String telephone;

    @Column(name = "user_image_name", length = 50)
    private String userImageName;

    @Basic
    @Column(name = "user_image_blob", columnDefinition = "LONGBLOB")
    private byte[] userImageBlob;

    @Column(name = "status", length = 1)
    private String status;

    
    @Column(name = "create_by", length = 50)
    private String createBy;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_date")
    private Timestamp createDate;

    @Column(name = "update_by", length = 50)
    private String updateBy;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "update_date")
    private Timestamp updateDate;

}
