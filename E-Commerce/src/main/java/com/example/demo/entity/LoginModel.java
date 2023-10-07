package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="userlogin")
public class LoginModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String token;
	private String logindatetime;
	private Long registrationid;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getLogindatetime() {
		return logindatetime;
	}
	public void setLogindatetime(String logindatetime) {
		this.logindatetime = logindatetime;
	}
	public Long getRegistrationid() {
		return registrationid;
	}
	public void setRegistrationid(Long registrationid) {
		this.registrationid = registrationid;
	}
	
}
