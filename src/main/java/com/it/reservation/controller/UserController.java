package com.it.reservation.controller;

import java.io.IOException;
import java.util.zip.DataFormatException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import org.springframework.web.multipart.MultipartFile;

import com.it.reservation.common.AbstractCommon;
import com.it.reservation.dto.request.UserDetailReqDTO;
import com.it.reservation.payload.ApiResponse;
import com.it.reservation.payload.CustomerUserAttr;
import com.it.reservation.service.UserService;
import com.it.reservation.util.AppConstants;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = AppConstants.PROJECT_VERSION + "/user")
public class UserController extends AbstractCommon{

	@Autowired
    UserService userService;

    @PostMapping(value = ("/updateImageProfile/{userId}"), consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> updateImageProfile(HttpServletRequest request, @RequestParam("file") MultipartFile file, @PathVariable Long userId) throws Exception {

        CustomerUserAttr userAttr = super.getCustomerUserAttr(request);
        ApiResponse response;

        try {
            response = getOkResponseData(userService.updateImageProfile(userAttr, file, userId));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getImageByte")
    public ResponseEntity<byte[]> getImageByte(HttpServletRequest request, @RequestParam(name = "userDetailId") Long userDetailId) throws IOException, DataFormatException {

        return ResponseEntity.ok(userService.getImageByte(userDetailId));
    }

    @PutMapping(value = ("/updateProfile/{userId}"))
    public ResponseEntity<ApiResponse> updateProfile(HttpServletRequest request, @RequestBody UserDetailReqDTO userDetailDTO, @PathVariable Long userId) throws Exception {

        CustomerUserAttr userAttr = super.getCustomerUserAttr(request);
        ApiResponse response;

        try {
            response = getOkResponseData(userService.updateProfile(userAttr, userDetailDTO, userId));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getById")
    public ResponseEntity<ApiResponse> getById(HttpServletRequest request, @RequestParam(name = "userId") Long userId) throws Exception {
        ApiResponse response;

        try {
            response = getOkResponseData(userService.getById(userId));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/getUserByUserId")
    public ResponseEntity<ApiResponse> getUserByUserId(HttpServletRequest request, @RequestParam(name = "userId") Long userId) throws Exception {
        ApiResponse response;

        try {
            response = getOkResponseData(userService.getUserByUserId(userId));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getCustomerAll")
    public ResponseEntity<ApiResponse> getCustomerAll(HttpServletRequest request) throws Exception {
        ApiResponse response;

        try {
            response = getOkResponseData(userService.getCustomerAll());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<ApiResponse> delete(HttpServletRequest request, @RequestParam(name = "userId") Long userId) throws Exception {

        ApiResponse response;

        try {
            userService.delete(userId);
            response = getSavedSuccessResponse();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return ResponseEntity.ok(response);
    }

    @PutMapping(value = ("/saveCustomer"))
    public ResponseEntity<ApiResponse> saveCustomer(HttpServletRequest request, @RequestBody UserDetailReqDTO userDetailDTO) throws Exception {

//        CustomerUserAttr userAttr = super.getCustomerUserAttr(request);
        ApiResponse response;

        try {
            response = getOkResponseData(userService.saveCustomer(userDetailDTO));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/checkUsreName")
    public ResponseEntity<ApiResponse> checkUsreName(HttpServletRequest request, @RequestParam(name = "userName") String userName) throws Exception {
        ApiResponse response;

        try {
            response = getOkResponseData(userService.checkUsreName(userName));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return ResponseEntity.ok(response);
    }
}
