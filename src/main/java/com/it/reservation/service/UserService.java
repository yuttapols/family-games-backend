package com.it.reservation.service;

import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import com.it.reservation.dto.request.UserDetailReqDTO;
import com.it.reservation.dto.response.AuthenticationResDTO;
import com.it.reservation.dto.response.UserDetailResDTO;
import com.it.reservation.payload.CustomerUserAttr;

public interface UserService {

	UserDetailsService userDetailsService() throws Exception;
	
    public Long updateImageProfile(CustomerUserAttr userAttr, MultipartFile file, Long userId) throws IOException, Exception;

    public byte[] getImageByte(Long userDetailId) throws IOException, DataFormatException;
    
    public Long updateProfile(CustomerUserAttr userAttr, UserDetailReqDTO userDetailReqDTO, Long userId) throws Exception;

    public UserDetailResDTO getById(Long userId) throws Exception;
    
    public AuthenticationResDTO getUserByUserId(Long userId) throws Exception;

    public List<UserDetailResDTO> getCustomerAll() throws Exception;

    public void delete(Long userId) throws IOException;

    public Long saveCustomer(UserDetailReqDTO userDetailReqDTO) throws Exception;

    public String checkUsreName(String userName) throws Exception;
}
