package com.it.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.it.reservation.common.AbstractCommon;
import com.it.reservation.dto.request.ReservationReqDTO;
import com.it.reservation.payload.ApiResponse;
import com.it.reservation.payload.CustomerUserAttr;
import com.it.reservation.service.ReservationService;
import com.it.reservation.util.AppConstants;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = AppConstants.PROJECT_VERSION + "/reservation")
public class ReservationController extends AbstractCommon{

	@Autowired
	ReservationService reservationService;
	
    @GetMapping("/getById")
    public ResponseEntity<ApiResponse> getById(HttpServletRequest request, @RequestParam(name = "revId") Long revId) throws Exception {
        ApiResponse response;

        try {
            response = getOkResponseData(reservationService.getRevById(revId));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/getRevByUserIdStatusWaiting")
    public ResponseEntity<ApiResponse> getRevByUserIdStatusWaiting(HttpServletRequest request, @RequestParam(name = "userId") Long userId) throws Exception {
        ApiResponse response;

        try {
            response = getOkResponseData(reservationService.getRevByUserIdStatusWaiting(userId));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/getRevAll")
    public ResponseEntity<ApiResponse> getRevAll(HttpServletRequest request) throws Exception {
        ApiResponse response;

        try {
            response = getOkResponseData(reservationService.getRevAll());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return ResponseEntity.ok(response);
    }
    
    @PostMapping(value = ("/saveRev"))
    public ResponseEntity<ApiResponse> saveRev(HttpServletRequest request, @RequestBody ReservationReqDTO req) throws Exception {

        CustomerUserAttr userAttr = super.getCustomerUserAttr(request);
        ApiResponse response;

        try {
            response = getOkResponseData(reservationService.saveRev(userAttr, req));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return ResponseEntity.ok(response);
    }
    
    @PutMapping(value = ("/updateRev/{revId}"))
    public ResponseEntity<ApiResponse> updateRev(HttpServletRequest request, @RequestBody ReservationReqDTO req, @PathVariable Long revId) throws Exception {

        CustomerUserAttr userAttr = super.getCustomerUserAttr(request);
        ApiResponse response;

        try {
            response = getOkResponseData(reservationService.updateRev(userAttr, req, revId));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping(value = "/deleteRev")
    public ResponseEntity<ApiResponse> deleteRev(HttpServletRequest request, @RequestParam(name = "revId") Long revId) throws Exception {

        ApiResponse response;

        try {
        	reservationService.deleteRev(revId);
            response = getSavedSuccessResponse();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/checkMaximumCancelRev")
    public ResponseEntity<ApiResponse> checkMaximumCancelRev(HttpServletRequest request, @RequestParam(name = "userId") Long userId) throws Exception {
        ApiResponse response;

        try {
            response = getOkResponseData(reservationService.checkMaximumCancelRev(userId));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/getSeatTypeAll")
    public ResponseEntity<ApiResponse> getSeatTypeAll(HttpServletRequest request) throws Exception {
        ApiResponse response;

        try {
            response = getOkResponseData(reservationService.getSeatTypeAll());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/getRevByStatusWaiting")
    public ResponseEntity<ApiResponse> getRevByStatusWaiting(HttpServletRequest request) throws Exception {
        ApiResponse response;

        try {
            response = getOkResponseData(reservationService.getRevByStatusWaiting());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/getAllRevByUserId")
    public ResponseEntity<ApiResponse> getAllRevByUserId(HttpServletRequest request, @RequestParam(name = "userId") Long userId) throws Exception {
        ApiResponse response;

        try {
            response = getOkResponseData(reservationService.getAllRevByUserId(userId));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/getAllRevByStatusWaiting")
    public ResponseEntity<ApiResponse> getAllRevByStatusWaiting(HttpServletRequest request) throws Exception {
        ApiResponse response;

        try {
            response = getOkResponseData(reservationService.getAllRevByStatusWaiting());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/getReasonCancelAll")
    public ResponseEntity<ApiResponse> getReasonCancelAll(HttpServletRequest request) throws Exception {
        ApiResponse response;

        try {
            response = getOkResponseData(reservationService.getReasonCancelAll());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return ResponseEntity.ok(response);
    }
}
