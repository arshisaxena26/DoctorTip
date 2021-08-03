package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user")
public class GenericUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;

	@NotNull(message = "User Name cannot be Null")
	@Column(name = "user_name", length = 50)
	private String userName;

	@NotNull(message = "User Email cannot be Null")
	@Email(message = "Invalid Email ID")
	@Column(name = "user_email", length = 60, unique = true)
	private String userEmail;

	@NotNull(message = "User Password cannot be Null")
	@Column(name = "user_password", length = 100)
	private String userPassword;

	@NotNull(message = "User Role cannot be Null")
	@Column(name = "user_role", length = 20)
	private String userRole;

	@Column(name = "user_is_blocked")
	private boolean isBlocked = false;

	public GenericUser() {
		// Default Constructor
	}

	public GenericUser(String userName, String userEmail, String userPassword, String userRole) {
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userRole = userRole;
	}

	public GenericUser(String userName) {
		this.userName = userName;
	}
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail + ", userPassword="
				+ userPassword + ", userRole=" + userRole + ", isBlocked=" + isBlocked + "]";
	}

}
