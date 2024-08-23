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
@Table(name = "reservation")
public class ReservationEntities {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	
	@Column(name = "user_id")
    private Long userId;
	
	@Column(name = "reason_cancel_id")
    private Long reasonCancelId;
	
	@Column(name = "rev_no", length = 3)
    private String revNo;
	
	@Column(name = "rev_no_number", length = 5)
    private Long revNoNumber;
	
	@Column(name = "seat_type_id", length = 5)
    private Long seatTypeId;
	
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "rev_time")
    private Timestamp revTime;
	
	
	@Column(name = "rev_status", length = 1)
    private String revStatus;
	
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
