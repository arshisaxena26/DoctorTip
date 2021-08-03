package com.app.dto;

import java.time.LocalDateTime;

public class PatientListDTO {

	private Integer id;
	private String gender;
	private byte[] image;
	private String location;
	private String name;
	private int age;
	private long lastLogin;
	private LocalDateTime lastLoginDate;
	private boolean isBlocked;

	public PatientListDTO() {
		// Default Constructor
	}

	public PatientListDTO(Integer id, String gender, byte[] image, String location, String name, int age,
			long lastLogin, LocalDateTime lastLoginDate, boolean isBlocked) {
		this.id = id;
		this.gender = gender;
		this.image = image;
		this.location = location;
		this.name = name;
		this.age = age;
		this.lastLogin = lastLogin;
		this.lastLoginDate = lastLoginDate;
		this.isBlocked = isBlocked;
	}

	public Integer getId() {
		return id;
	}

	public String getGender() {
		return gender;
	}

	public byte[] getImage() {
		return image;
	}

	public String getLocation() {
		return location;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public long getLastLogin() {
		return lastLogin;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public LocalDateTime getLastLoginDate() {
		return lastLoginDate;
	}
}
