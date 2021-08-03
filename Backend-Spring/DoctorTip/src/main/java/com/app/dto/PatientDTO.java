package com.app.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

@SuppressWarnings("serial")
public class PatientDTO implements Serializable {

	private int userId;
	private String patientBloodGroup;
	private int patientAge;
	private String patientLocation;
	private String patientGender;
	private String patientPhone;
	private MultipartFile patientImage;

	public PatientDTO() {
		// Default Constructor
	}

	public PatientDTO(int userId, String patientBloodGroup, int patientAge, String patientLocation,
			String patientGender, String patientPhone, MultipartFile patientImage) {
		this.userId = userId;
		this.patientBloodGroup = patientBloodGroup;
		this.patientAge = patientAge;
		this.patientLocation = patientLocation;
		this.patientGender = patientGender;
		this.patientPhone = patientPhone;
		this.patientImage = patientImage;
	}

	public PatientDTO(String patientBloodGroup, int patientAge, String patientLocation, String patientGender,
			String patientPhone) {
		this.patientBloodGroup = patientBloodGroup;
		this.patientAge = patientAge;
		this.patientLocation = patientLocation;
		this.patientGender = patientGender;
		this.patientPhone = patientPhone;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPatientBloodGroup() {
		return patientBloodGroup;
	}

	public void setPatientBloodGroup(String patientBloodGroup) {
		this.patientBloodGroup = patientBloodGroup;
	}

	public int getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(int patientAge) {
		this.patientAge = patientAge;
	}

	public String getPatientLocation() {
		return patientLocation;
	}

	public void setPatientLocation(String patientLocation) {
		this.patientLocation = patientLocation;
	}

	public String getPatientGender() {
		return patientGender;
	}

	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}

	public String getPatientPhone() {
		return patientPhone;
	}

	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}

	public MultipartFile getPatientImage() {
		return patientImage;
	}

	public void setPatientImage(MultipartFile patientImage) {
		this.patientImage = patientImage;
	}

	@Override
	public String toString() {
		return "PatientDTO [userId=" + userId + ", patientBloodGroup=" + patientBloodGroup + ", patientAge="
				+ patientAge + ", patientLocation=" + patientLocation + ", patientGender=" + patientGender
				+ ", patientPhone=" + patientPhone + "]";
	}

}
