package com.example.demoecommerceservice;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface ECommerceService {
	
	public ResponseEntity<?> saveregistration(Map<String, Object> param)throws Exception;

}
