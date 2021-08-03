package com.app.dto;

import org.springframework.web.multipart.MultipartFile;

public class DoctorDTO {
	private int userId;
	private String doctorSpecialization;
	private int doctorExperience;
	private String doctorLocation;
	private String doctorClinicName;
	private double doctorConsultationFee;
	private String doctorGender;
	private String doctorPhone;
	private MultipartFile doctorImage;

	public DoctorDTO() {
		// Default Constructor
	}

	public DoctorDTO(int userId, String doctorSpecialization, int doctorExperience, String doctorLocation,
			String doctorClinicName, double doctorConsultationFee, String doctorGender, String doctorPhone,
			MultipartFile doctorImage) {
		this.userId = userId;
		this.doctorSpecialization = doctorSpecialization;
		this.doctorExperience = doctorExperience;
		this.doctorLocation = doctorLocation;
		this.doctorClinicName = doctorClinicName;
		this.doctorConsultationFee = doctorConsultationFee;
		this.doctorGender = doctorGender;
		this.doctorPhone = doctorPhone;
		this.doctorImage = doctorImage;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getDoctorSpecialization() {
		return doctorSpecialization;
	}

	public void setDoctorSpecialization(String doctorSpecialization) {
		this.doctorSpecialization = doctorSpecialization;
	}

	public int getDoctorExperience() {
		return doctorExperience;
	}

	public void setDoctorExperience(int doctorExperience) {
		this.doctorExperience = doctorExperience;
	}

	public String getDoctorLocation() {
		return doctorLocation;
	}

	public void setDoctorLocation(String doctorLocation) {
		this.doctorLocation = doctorLocation;
	}

	public String getDoctorClinicName() {
		return doctorClinicName;
	}

	public void setDoctorClinicName(String doctorClinicName) {
		this.doctorClinicName = doctorClinicName;
	}

	public double getDoctorConsultationFee() {
		return doctorConsultationFee;
	}

	public void setDoctorConsultationFee(double doctorConsultationFee) {
		this.doctorConsultationFee = doctorConsultationFee;
	}

	public String getDoctorGender() {
		return doctorGender;
	}

	public void setDoctorGender(String doctorGender) {
		this.doctorGender = doctorGender;
	}

	public String getDoctorPhone() {
		return doctorPhone;
	}

	public void setDoctorPhone(String doctorPhone) {
		this.doctorPhone = doctorPhone;
	}

	public MultipartFile getDoctorImage() {
		return doctorImage;
	}

	public void setDoctorImage(MultipartFile doctorImage) {
		this.doctorImage = doctorImage;
	}

	@Override
	public String toString() {
		return "DoctorDTO [doctorSpecialization=" + doctorSpecialization + ", doctorExperience=" + doctorExperience
				+ ", doctorLocation=" + doctorLocation + ", doctorClinicName=" + doctorClinicName
				+ ", doctorConsultationFee=" + doctorConsultationFee + ", doctorGender=" + doctorGender
				+ ", doctorPhone=" + doctorPhone + ", doctorImage=" + doctorImage + "]";
	}

}
