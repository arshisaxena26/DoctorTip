package com.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "likes_dislikes")
public class Likes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "like_id")
	private Integer likeId;

	@Column(name = "is_liked")
	private boolean isLiked = false;

	@Column(name = "is_disliked")
	private boolean isDisliked = false;

	// Unidirectional Mapping
	@ManyToOne(targetEntity = Doctor.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;

	// Unidirectional Mapping
	@ManyToOne(targetEntity = Patient.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id")
	private Patient patient;

	public Likes() {
		// Default Constructor
	}

	public Likes(boolean isLiked, boolean isDisliked) {
		this.isLiked = isLiked;
		this.isDisliked = isDisliked;
	}

	public Likes(boolean isLiked, boolean isDisliked, Doctor doctor, Patient patient) {
		super();
		this.isLiked = isLiked;
		this.isDisliked = isDisliked;
		this.doctor = doctor;
		this.patient = patient;
	}

	public Integer getLikeId() {
		return likeId;
	}

	public void setLikeId(Integer likeId) {
		this.likeId = likeId;
	}

	public boolean isLiked() {
		return isLiked;
	}

	public void setLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}

	public boolean isDisliked() {
		return isDisliked;
	}

	public void setDisliked(boolean isDisliked) {
		this.isDisliked = isDisliked;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public String toString() {
		return "Likes [likeId=" + likeId + ", isLiked=" + isLiked + ", isDisliked=" + isDisliked + "]";
	}
}
