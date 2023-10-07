package com.example.demoecommerceservice;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Date;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;


public class DataTypeUtility {

	public static String stringvalue(Object obj) {
		if(obj instanceof Long) {
			return String.valueOf(obj);
		} else if(obj instanceof Character) {
			return String.valueOf(obj);
		} else if(obj instanceof Integer) {
			return String.valueOf(obj);
		} else if(obj instanceof Double) {
			return String.valueOf(obj);
		} else if(obj instanceof Float) {
			return String.valueOf(obj);
		} else {
			return String.valueOf(obj);
		}	
	}
	
	public static  String encryptvalue(String st) {
		 try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(st.getBytes());  
             byte[] bytes = digest.digest();  
             StringBuilder s = new StringBuilder();  
            for(int i=0; i< bytes.length ;i++)  
            {  
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
            }  
            return s.toString();
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
			return null;
		}  
		
	}
	
	
public static  String getcurrentdatetime() {
	 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
	   LocalDateTime now = LocalDateTime.now();  
	   String currentdate = dtf.format(now);
	return currentdate;
}

private static final SecureRandom secureRandom = new SecureRandom();
private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

public static String generateNewToken() {
    byte[] randomBytes = new byte[24];
    secureRandom.nextBytes(randomBytes);
    return base64Encoder.encodeToString(randomBytes);

}


}
