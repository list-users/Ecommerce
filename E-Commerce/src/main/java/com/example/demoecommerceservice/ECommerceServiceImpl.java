package com.example.demoecommerceservice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.RegistrationModel;
import com.example.demo.entity.RegistrationRepo;

@Service
public class ECommerceServiceImpl implements ECommerceService {

	@Autowired
	 RegistrationRepo registrationRepo;
	
	@Override
	public ResponseEntity<?> saveregistration(Map<String, Object> param)throws Exception{
		Map<String, Object> result = new HashMap<>();
		try {
		String name = (String)param.get("name");
		String email = (String)param.get("email");
		String password = (String)param.get("password");
		String phoneno = (String)param.get("phoneno");
		
		RegistrationModel model =new RegistrationModel();
		model.setName(name);
		model.setPassword(password);
		model.setEmail(email);
		model.setPhoneno(phoneno);
		registrationRepo.save(model);
		}catch (Exception e) {
			// TODO: handle exception
		}
		result.put("success" , "success");
		return new ResponseEntity("" ,HttpStatus.OK);
	}

	

}
