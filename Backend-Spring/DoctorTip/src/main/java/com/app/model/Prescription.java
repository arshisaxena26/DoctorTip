package com.app.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "prescription")
public class Prescription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prescription_id")
	private Integer prescriptionId;

	@NotNull
	@Column(name = "prescription_content", length = 1000)
	private String prescriptionContent;

	@NotNull
	@Column(name = "prescription_dos", length = 500)
	private String doS;

	@NotNull
	@Column(name = "prescription_donts", length = 500)
	private String dontS;

	@NotNull
	@Column(name = "prescription_follow_up")
	private Date followUp;
    @JsonIgnore
	@OneToOne
	@JoinColumn(name = "appointment_id")
	private Appointment appointment;

	public Prescription() {
		// Default Constructor
	}

	public Prescription(String prescriptionContent, String doS, String dontS, Date followUp,
			Appointment appointmentId) {
		this.prescriptionContent = prescriptionContent;
		this.doS = doS;
		this.dontS = dontS;
		this.followUp = followUp;
		this.appointment = appointmentId;
	}

	public Integer getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(Integer prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	public String getPrescriptionContent() {
		return prescriptionContent;
	}

	public void setPrescriptionContent(String prescriptionContent) {
		this.prescriptionContent = prescriptionContent;
	}

	public String getDoS() {
		return doS;
	}

	public void setDoS(String doS) {
		this.doS = doS;
	}

	public String getDontS() {
		return dontS;
	}

	public void setDontS(String dontS) {
		this.dontS = dontS;
	}

	public Date getFollowUp() {
		return followUp;
	}

	public void setFollowUp(Date followUp) {
		this.followUp = followUp;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	@Override
	public String toString() {
		return "Prescription [prescriptionId=" + prescriptionId + ", prescriptionContent=" + prescriptionContent
				+ ", doS=" + doS + ", dontS=" + dontS + "]";
	}
}
