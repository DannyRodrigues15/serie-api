package com.devvision.series.app.core.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.devvision.series.app.core.excepions.RunTimeException;

import java.util.Base64;

@Component
public class EncryptHash {
	/**
	 * deve ter 16 caracteres
	 */
	private static final String KEY_ONE = "QhF2I=lM5v=cDw29";
	
	/**
	 * deve ter 24 caracteres
	 */
	private static final String KEY_TWO = "0CjVx8tAD=US=uOzRDydEO11";
	
	/**
	 * deve ter 11 caracteres
	 */
	private static final String KEY_TREE = "8D=U4YeW=41";
	
	/**
	 * deve ter 13 caracteres
	 */
	private static final String KEY_FOUR = "9GuU=Q3oFlbP7";
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public String encode(String value) {
		try {
			String encryptedValue = Base64.getEncoder().encodeToString(value.getBytes());
	
			String st1 = encryptedValue.substring(0, 1);
			String st2 = encryptedValue.substring(1, 2);
			String st3 = encryptedValue.substring(2, 3);
			String st4 = encryptedValue.substring(3);
			
			String idEncrypted = KEY_ONE + st1 + KEY_TWO + st2 + KEY_TREE + st3 + KEY_FOUR + st4;
			String encoded = Base64.getEncoder().encodeToString(idEncrypted.getBytes());
		
		    return encoded;
		} catch (Exception e) {
			throw new RunTimeException("Encode Error: " + e);
		}
	}
	
	public String decode(String value) {
		try {
			byte[] decryptedValueByte = Base64.getDecoder().decode(value);
			String decryptingValue = new String(decryptedValueByte);
	
			String st1 = decryptingValue.substring(16, 17);
			String st2 = decryptingValue.substring(41, 42);
			String st3 = decryptingValue.substring(53, 54);
			String st4 = decryptingValue.substring(67);
			String idDecrypting = st1 + st2 + st3 + st4;
	
			byte[] decodeding = Base64.getDecoder().decode(idDecrypting);
			String decoded = new String(decodeding);
			
		    return decoded;
		} catch (Exception e) {
			throw new RunTimeException("Decode Error: " + e);
		}
	}

	public String getHash(String value) {
		return passwordEncoder.encode(value);
	}
	
	public boolean compareValueHash(String value, String valueHash) {
		return passwordEncoder.matches(value, valueHash);
	}
}