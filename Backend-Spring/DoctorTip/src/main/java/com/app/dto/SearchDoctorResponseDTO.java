package com.app.dto;

public class SearchDoctorResponseDTO {
	private int id;
	private String specialization;
	private String name;
	private int experience;
	private String location;
	private String clinicName;
	private double consultationFee;
	private String phone;
	private byte[] image;
	private int rating;

	public SearchDoctorResponseDTO() {
		// Default Constructor
	}

	public SearchDoctorResponseDTO(int id, String specialization, String name, int experience, String location,
			String clinicName, double consultationFee, String phone, byte[] image, int rating) {
		super();
		this.id = id;
		this.specialization = specialization;
		this.name = name;
		this.experience = experience;
		this.location = location;
		this.clinicName = clinicName;
		this.consultationFee = consultationFee;
		this.phone = phone;
		this.image = image;
		this.rating = rating;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getClinicName() {
		return clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}

	public double getConsultationFee() {
		return consultationFee;
	}

	public void setConsultationFee(double consultationFee) {
		this.consultationFee = consultationFee;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

}
