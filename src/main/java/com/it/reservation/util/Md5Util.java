package com.it.reservation.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Util {

	public static String genarateMd5(String value) throws NoSuchAlgorithmException {
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");  
			byte[] messageDigest = md.digest(value.getBytes()); 
			BigInteger no = new BigInteger(1, messageDigest);

			String hashtext = no.toString(16);  
			while (hashtext.length() < 32){  
				hashtext = "0" + hashtext;  
			} 
			return hashtext;  
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);  
		}
	}
	
	public static String checkMd5(String value) {
		
		return DigestUtils.md5Hex(value);
	}
}
