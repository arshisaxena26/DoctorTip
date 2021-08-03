package com.app.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "doctor_availablity")
public class DoctorAvailablity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "availablity_id")
	private Integer availablityId;

	@NotNull
	@Column(name = "availablity_from")
	private LocalDate availablityFrom;

	@NotNull
	@Column(name = "availablity_to")
	private LocalDate availablityTo;

//	@ManyToOne(cascade = CascadeType.ALL)
//	private Doctor doctor;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;

	public DoctorAvailablity() {
		// Default Constructor
	}

	public DoctorAvailablity(@NotNull LocalDate availablityFrom, @NotNull LocalDate availablityTo) {
		this.availablityFrom = availablityFrom;
		this.availablityTo = availablityTo;
	}

	public Integer getAvailablityId() {
		return availablityId;
	}

	public void setAvailablityId(Integer availablityId) {
		this.availablityId = availablityId;
	}

	public LocalDate getAvailablityFrom() {
		return availablityFrom;
	}

	public void setAvailablityFrom(LocalDate availablityFrom) {
		this.availablityFrom = availablityFrom;
	}

	public LocalDate getAvailablityTo() {
		return availablityTo;
	}

	public void setAvailablityTo(LocalDate availablityTo) {
		this.availablityTo = availablityTo;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	@Override
	public String toString() {
		return "DoctorAvailablity [availablityId=" + availablityId + ", availablityFrom=" + availablityFrom
				+ ", availablityTo=" + availablityTo + "]";
	}
}
