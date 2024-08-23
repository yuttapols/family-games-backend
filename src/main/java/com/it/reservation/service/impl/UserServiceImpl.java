package com.it.reservation.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.it.reservation.dto.request.UserDetailReqDTO;
import com.it.reservation.dto.response.AuthenticationResDTO;
import com.it.reservation.dto.response.UserDetailResDTO;
import com.it.reservation.entities.AuthenticationEntities;
import com.it.reservation.entities.UserDetailEntities;
import com.it.reservation.payload.CustomerUserAttr;
import com.it.reservation.repository.AuthenticationRepository;
import com.it.reservation.repository.UserDetailRepository;
import com.it.reservation.service.UserService;
import com.it.reservation.util.Constants;
import com.it.reservation.util.DateUtil;
import com.it.reservation.util.FunctionUtil;
import com.it.reservation.util.ImgUtils;
import com.it.reservation.util.Md5Util;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	AuthenticationRepository authenticationRepository;
	
    @Autowired
    UserDetailRepository userDeatilRepository;
    
    @Autowired
    ModelMapper mapper;
	
	@Override
	public UserDetailsService userDetailsService() throws Exception {
		return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {

                return authenticationRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Long updateImageProfile(CustomerUserAttr userAttr, MultipartFile file, Long userId)
			throws IOException, Exception {
		Long response = null;
        if (null != file && null != userId) {

            UserDetailEntities userDtEntity = userDeatilRepository.findByUserId(userId);

            if (ObjectUtils.isNotEmpty(userDtEntity)) {

                String fileName = ImgUtils.getFileNameImages(file);
                userDtEntity.setUserImageBlob(ImgUtils.compressImage(file.getBytes()));
                userDtEntity.setUserImageName(fileName);

                userDeatilRepository.saveAndFlush(userDtEntity);
                response = userDtEntity.getId();
            }
        }
        return response;
	}

	@Override
	@Transactional(readOnly = true)
	public byte[] getImageByte(Long userDetailId) throws IOException, DataFormatException {
        UserDetailEntities userImage = userDeatilRepository.findById(userDetailId).get();

        if (ObjectUtils.isNotEmpty(userImage)) {
            if (StringUtils.isNotBlank(userImage.getUserImageName())) {

                return ImgUtils.decompressImage(userImage.getUserImageBlob());
            }
        }

        return null;
    }

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Long updateProfile(CustomerUserAttr userAttr, UserDetailReqDTO userDetailReqDTO, Long userId)
			throws Exception {
		Long response = null;
        if (ObjectUtils.isNotEmpty(userDetailReqDTO) && null != userId) {

            UserDetailEntities userDtEntities = userDeatilRepository.findByUserId(userId);

            if (ObjectUtils.isNotEmpty(userDtEntities)) {
            	userDtEntities.setFristName(userDetailReqDTO.getFristName());
            	userDtEntities.setLastName(userDetailReqDTO.getLastName());
            	userDtEntities.setEmail(userDetailReqDTO.getEmail());
            	userDtEntities.setTelephone(userDetailReqDTO.getTelephone());

            	userDtEntities.setUpdateBy(userAttr.getCustomerNo());
            	userDtEntities.setUpdateDate(DateUtil.createTimestmapNow());
                userDeatilRepository.save(userDtEntities);

                response = userDtEntities.getUserId();
            }
        }
        return response;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetailResDTO getById(Long userId) throws Exception {
		UserDetailResDTO resp = null;
        if (null != userId) {
            UserDetailEntities userDtEntities = userDeatilRepository.findByUserId(userId);
            resp = mapper.map(userDtEntities, new TypeToken<UserDetailResDTO>() {
            }.getType());
        }

        return resp;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserDetailResDTO> getCustomerAll() throws Exception {
		List<UserDetailResDTO> resp = null;

        List<AuthenticationEntities> authenEntityList = authenticationRepository.findAllByCustomer();

        if (CollectionUtils.isNotEmpty(authenEntityList)) {
            resp = new ArrayList<>();
            for (AuthenticationEntities authen : authenEntityList) {

                UserDetailResDTO userDt = mapper.map(userDeatilRepository.findByUserId(authen.getId()), new TypeToken<UserDetailResDTO>() {
                }.getType());

                resp.add(userDt);
            }
        }
        return resp;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Long userId) throws IOException {
        authenticationRepository.deleteByUserId(userId);
        userDeatilRepository.deleteByUserId(userId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Long saveCustomer(UserDetailReqDTO userDetailReqDTO) throws Exception {
		Long response = null;
        if (ObjectUtils.isNotEmpty(userDetailReqDTO)) {

            if ("99".equals(checkUsreName(userDetailReqDTO.getUserName()))) {
                return null;
            }

            AuthenticationEntities authenEntity = new AuthenticationEntities();
            
            String customerNo = FunctionUtil.genarateCustomerNo(authenticationRepository.findAllByCustomer().size(), Constants.USER.CUSTOMER_PREFIX);

            authenEntity.setUserName(userDetailReqDTO.getUserName());
            authenEntity.setPassword(Md5Util.genarateMd5(userDetailReqDTO.getPassword()));
            authenEntity.setRoleId(Constants.USER.ROLE_CUSTOMER);
            authenEntity.setStatus(Constants.STATUS_NORMAL);
            authenEntity.setCreateBy(customerNo);
            authenEntity.setCreateDate(DateUtil.createTimestmapNow());

            authenEntity = authenticationRepository.save(authenEntity);

            if (ObjectUtils.isNotEmpty(authenEntity)) {
                UserDetailEntities userDtEntity = new UserDetailEntities();

                userDtEntity.setUserId(authenEntity.getId());
                userDtEntity.setCustomerNo(customerNo);
                userDtEntity.setFristName(userDetailReqDTO.getFristName());
                userDtEntity.setLastName(userDetailReqDTO.getLastName());
                userDtEntity.setEmail(userDetailReqDTO.getEmail());
                userDtEntity.setTelephone(userDetailReqDTO.getTelephone());
                userDtEntity.setStatus(Constants.STATUS_NORMAL);
                userDtEntity.setCreateBy(customerNo);
                userDtEntity.setCreateDate(DateUtil.createTimestmapNow());
                userDeatilRepository.save(userDtEntity);
            }

            response = authenEntity.getId();
        }
        return response;
	}

	@Override
	@Transactional(readOnly = true)
	public String checkUsreName(String userName) throws Exception {
        String resp = Constants.STATUS_CODE_SUCESS;

        Optional<AuthenticationEntities> authen = authenticationRepository.findByUserName(userName);
        if (authen.isPresent()) {
            resp = Constants.STATUS_CODE_UNSUCESS;
        }

        return resp;
	}

	@Override
	@Transactional(readOnly = true)
	public AuthenticationResDTO getUserByUserId(Long userId) throws Exception {

        if (null != userId) {
        	Optional<AuthenticationEntities> authenticationEntities = authenticationRepository.findById(userId);
        	if(authenticationEntities.isPresent()) {
        		UserDetailEntities userDtEntities = userDeatilRepository.findByUserId(userId);
        		
        		return 
        				AuthenticationResDTO.builder()
        					.id(authenticationEntities.get().getId())
        					.status(authenticationEntities.get().getStatus())
        					.role(authenticationEntities.get().getRole())
        					.userDetail(mapper.map(userDtEntities, new TypeToken<UserDetailResDTO>() {
        						}.getType())).build();
        	}

        }

        return null;
	}

}
