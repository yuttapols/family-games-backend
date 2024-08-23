package com.it.reservation.service;

import java.util.List;

import com.it.reservation.dto.request.ReservationReqDTO;
import com.it.reservation.dto.response.ReasonCancelResDTO;
import com.it.reservation.dto.response.ReservationResDTO;
import com.it.reservation.dto.response.SeatTypeResDTO;
import com.it.reservation.payload.CustomerUserAttr;

public interface ReservationService {
	
	public ReservationResDTO getRevById(Long revId) throws Exception;

	public ReservationResDTO getRevByUserIdStatusWaiting(Long userId) throws Exception;
	
	public List<ReservationResDTO> getRevAll() throws Exception;
	
	public Long saveRev(CustomerUserAttr userAttr, ReservationReqDTO req) throws Exception;
	
	public Long updateRev(CustomerUserAttr userAttr, ReservationReqDTO req, Long revId) throws Exception;
	
	public void deleteRev(Long revId) throws Exception;
	
	public String checkMaximumCancelRev(Long userId) throws Exception;
	
	public Integer getRevByStatusWaiting() throws Exception;
	
	public List<ReservationResDTO> getAllRevByUserId(Long userId) throws Exception;
	
	public List<ReservationResDTO> getAllRevByStatusWaiting() throws Exception;
	
	// Seat Type
	public List<SeatTypeResDTO> getSeatTypeAll() throws Exception;
	
	// Reason
	public List<ReasonCancelResDTO> getReasonCancelAll() throws Exception;
	
	
}
