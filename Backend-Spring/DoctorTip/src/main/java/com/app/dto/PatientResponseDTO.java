package com.app.dto;

public class PatientResponseDTO {
	private int userId;
	private String name;

	private String patientBloodGroup;
	private int patientAge;
	private String patientLocation;
	private String patientGender;
	private String patientPhone;
	private byte[] patientImage;

	public PatientResponseDTO() {
		// Default Constructor
	}

	public PatientResponseDTO(int userId, String patientBloodGroup, int patientAge, String patientLocation,
			String patientGender, String patientPhone, byte[] patientImage, String name) {
		this.userId = userId;
		this.patientBloodGroup = patientBloodGroup;
		this.patientAge = patientAge;
		this.patientLocation = patientLocation;
		this.patientGender = patientGender;
		this.patientPhone = patientPhone;
		this.patientImage = patientImage;
		this.name = name;
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

	public byte[] getPatientImage() {
		return patientImage;
	}

	public void setPatientImage(byte[] patientImage) {
		this.patientImage = patientImage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "PatientDTO [userId=" + userId + ", patientBloodGroup=" + patientBloodGroup + ", patientAge="
				+ patientAge + ", patientLocation=" + patientLocation + ", patientGender=" + patientGender
				+ ", patientPhone=" + patientPhone + "]";
	}

}
