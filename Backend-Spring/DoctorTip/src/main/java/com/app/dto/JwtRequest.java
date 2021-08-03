package com.app.dto;

import java.io.Serializable;

public class JwtRequest implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;

	private String userEmail;
	private String userPassword;

	public JwtRequest() {
		// need default constructor for JSON Parsing
	}

	public JwtRequest(String userEmail, String password) {

		this.userEmail = userEmail;
		this.userPassword = password;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}