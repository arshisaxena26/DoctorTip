package com.app.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "appointment")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "appointment_id")
	private Integer appointmentId;

	@NotNull(message = "Appointment Date cannot be Null")
	@Column(name = "appointment_date")
	private LocalDate appointmentDate;

	@Column(name = "appointment_status", length = 20)
	private String appointmentStatus = "Booked";

	@Nullable
	@Column(name = "feedback_id")
	private Integer feedbackId;
	
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;

	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;
	
	@OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
	private Prescription prescription;
	
	@OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
	private Concern concern;
	
	@OneToOne(targetEntity = TimeSlot.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "time_slot_id")
	private TimeSlot timeSlot;

	public Appointment() {
		// Default Constructor
	}

	public Appointment(LocalDate appointmentDate, String appointmentStatus,Integer feedbackId, Patient patient, Doctor doctor,
			TimeSlot timeSlot) {
		this.appointmentDate = appointmentDate;
		this.appointmentStatus = appointmentStatus;
		this.feedbackId=feedbackId;
		this.patient = patient;
		this.doctor = doctor;
		this.timeSlot = timeSlot;
	}

	public Integer getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getAppointmentStatus() {
		return appointmentStatus;
	}

	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	

	public Prescription getPrescription() {
		return prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

	public Concern getConcern() {
		return concern;
	}

	public void setConcern(Concern concern) {
		this.concern = concern;
	}

	public TimeSlot getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(TimeSlot timeSlot) {
		this.timeSlot = timeSlot;
	}

	public Integer getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(Integer feedbackId) {
		this.feedbackId = feedbackId;
	}

	@Override
	public String toString() {
		return "Appointment [appointmentId=" + appointmentId + ", appointmentDate=" + appointmentDate
				+ ", appointmentStatus=" + appointmentStatus + "]";
	}
}
