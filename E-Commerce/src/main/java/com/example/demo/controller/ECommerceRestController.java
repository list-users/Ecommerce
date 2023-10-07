package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.LoginModel;
import com.example.demo.entity.LoginRepo;
import com.example.demo.entity.RegistrationModel;
import com.example.demo.entity.RegistrationRepo;
import com.example.demoecommerceservice.DataTypeUtility;
import com.example.demoecommerceservice.ECommerceService;

@RestController
@CrossOrigin(origins = "*" , allowedHeaders = "*")
@RequestMapping(value = "/api/mobile")
public class ECommerceRestController {
	
	@Autowired
	private RegistrationRepo registrationRepo;
	@Autowired
	private LoginRepo loginRepo;
	
	
	@PostMapping(value = "/registration")
	public ResponseEntity<?>saveregistration(@RequestBody Map<String, Object>param)throws Exception{
		String email = (String)param.get("email");
		RegistrationModel model1 =registrationRepo.findByEmail(email);
		if(model1!=null) {
			return  new ResponseEntity("failed" , HttpStatus.OK);
		} else {
		try {
		
		String name = (String)param.get("name");
		
		String password = null;
		try {
			password = DataTypeUtility.encryptvalue((String) param.get("password"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String phoneno = (String)param.get("phoneno");
		RegistrationModel model =new RegistrationModel();
		model.setName(name);
		model.setPassword(password);
		model.setEmail(email);
		model.setPhoneno(phoneno);
		registrationRepo.save(model);
		}catch (Exception e) {
		}
		return new ResponseEntity("success" , HttpStatus.OK);
		}
	}
	
	@PostMapping(value = "/login")
	public ResponseEntity<?>login(@RequestBody Map<String, Object>param)throws Exception{
		Map<String, Object> result = new HashMap<>();
		String email = (String)param.get("email");
		RegistrationModel model1 =registrationRepo.findByEmail(email);
		if(model1!=null) {
			String password = null;
			try {
				password = DataTypeUtility.encryptvalue((String) param.get("password"));
				if(model1.getPassword().equalsIgnoreCase(password)) {
					String token=DataTypeUtility.generateNewToken();
					LoginModel loginModel =new LoginModel();
					loginModel.setToken(token);
					loginModel.setRegistrationid(model1.getId());
					loginModel.setLogindatetime(DataTypeUtility.getcurrentdatetime());
					loginRepo.save(loginModel);
					
					result.put("loginmodel", loginModel);
					result.put("registrationmodel", model1);
					
					return new ResponseEntity(result ,HttpStatus.OK);
				} else {
					result.put("failed" , "Invalid Password");
					return new ResponseEntity("Failed" , HttpStatus.OK);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ResponseEntity("Success" , HttpStatus.OK);
			
		} else {
			result.put("failed" , "Invalid User Name");
			return new ResponseEntity("Failed" , HttpStatus.OK);
		}
		
}
}
