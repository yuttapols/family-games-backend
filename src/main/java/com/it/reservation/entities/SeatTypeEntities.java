package com.it.reservation.entities;



import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Table(name = "seat_type")
public class SeatTypeEntities {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	
	@Column(name = "seat_type_name_th", length = 50)
    private String seatTypeNameTh;
	
	@Column(name = "seat_type_name_en", length = 50)
    private String seatTypeNameEn;
	
	@Column(name = "seat_type_unit", length = 10)
    private String seatTypeUnit;
	
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