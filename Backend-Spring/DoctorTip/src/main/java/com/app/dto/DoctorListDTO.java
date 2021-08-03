package com.app.dto;

import java.util.List;

import com.app.model.Comment;
import com.app.model.DoctorAvailablity;

public class DoctorListDTO {

	private Integer id;
	private String specialization;
	private String clinicName;
	private double consulationFees;
	private int experience;
	private byte[] image;
	private String location;
	private String name;
	private List<Comment> comments;
	private List<DoctorAvailablity> availablity;

	public DoctorListDTO() {
		// Default Constructor
	}

	public DoctorListDTO(Integer id, String specialization, String clinicName, double consulationFees, int experience,
			byte[] image, String location, String name) {
		this.id = id;
		this.specialization = specialization;
		this.clinicName = clinicName;
		this.consulationFees = consulationFees;
		this.experience = experience;
		this.image = image;
		this.location = location;
		this.name = name;
	}

	public DoctorListDTO(Integer id, String specialization, String clinicName, double consulationFees, int experience,
			byte[] image, String location, String name, List<Comment> comments, List<DoctorAvailablity> availablity) {
		super();
		this.id = id;
		this.specialization = specialization;
		this.clinicName = clinicName;
		this.consulationFees = consulationFees;
		this.experience = experience;
		this.image = image;
		this.location = location;
		this.name = name;
		this.comments = comments;
		this.availablity = availablity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getClinicName() {
		return clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}

	public double getConsulationFees() {
		return consulationFees;
	}

	public void setConsulationFees(double consulationFees) {
		this.consulationFees = consulationFees;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public List<DoctorAvailablity> getAvailablity() {
		return availablity;
	}
}