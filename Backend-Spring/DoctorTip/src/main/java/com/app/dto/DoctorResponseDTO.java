package com.app.dto;

public class DoctorResponseDTO {
	private Integer doctorId;
	private String name;
	private String email;
	private String doctorSpecialization;
	private int doctorExperience;
	private String doctorLocation;
	private String doctorClinicName;
	private double doctorConsultationFee;
	private String doctorGender;
	private String doctorPhone;
	private byte[] doctorImage;

	public DoctorResponseDTO() {
		// Default Constructor
	}

	public DoctorResponseDTO(Integer doctorId, String name, String email, String doctorSpecialization,
			int doctorExperience, String doctorLocation, String doctorClinicName, double doctorConsultationFee,
			String doctorGender, String doctorPhone, byte[] doctorImage) {
		super();
		this.doctorId = doctorId;
		this.name = name;
		this.email = email;
		this.doctorSpecialization = doctorSpecialization;
		this.doctorExperience = doctorExperience;
		this.doctorLocation = doctorLocation;
		this.doctorClinicName = doctorClinicName;
		this.doctorConsultationFee = doctorConsultationFee;
		this.doctorGender = doctorGender;
		this.doctorPhone = doctorPhone;
		this.doctorImage = doctorImage;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public byte[] getDoctorImage() {
		return doctorImage;
	}

	public void setDoctorImage(byte[] doctorImage) {
		this.doctorImage = doctorImage;
	}

}
