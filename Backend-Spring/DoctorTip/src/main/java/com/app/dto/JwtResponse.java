package com.app.dto;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private int userId;
	private String userName;
	private String role;
	private boolean foundEntry;

	public JwtResponse() {
		this.jwttoken = "";

	}

	public JwtResponse(String jwttoken, int userId, String userName, String role, boolean foundEntry) {
		super();
		this.jwttoken = jwttoken;
		this.userId = userId;
		this.userName = userName;
		this.role = role;
		this.foundEntry = foundEntry;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getJwttoken() {
		return jwttoken;
	}

	public boolean isFoundEntry() {
		return foundEntry;
	}

	public void setFoundEntry(boolean foundEntry) {
		this.foundEntry = foundEntry;
	}

}