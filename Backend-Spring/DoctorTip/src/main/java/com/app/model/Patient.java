package com.app.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

@Entity
@Table(name = "patient")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "patient_id")
	private Integer patientId;

	@NotNull(message = "Patient Blood Group cannot be Null")
	@Column(name = "patient_blood_group", length = 5)
	private String patientBloodGroup;

	@NotNull(message = "Patient Age cannot be Null")
	@Column(name = "patient_age")
	private int patientAge;

	@NotNull(message = "Patient Location cannot be Null")
	@Column(name = "patient_location", length = 30)
	private String patientLocation;

	@Nullable
	@Column(name = "patient_gender", length = 20)
	private String patientGender;

	@Nullable
	@Column(name = "patient_phone", length = 15)
	private String patientPhone;

	@Nullable
	@Lob
	@Column(name = "patient_image", length = 150)
	private byte[] patientImage;

	@OneToOne(targetEntity = GenericUser.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private GenericUser user;

	@OneToMany(mappedBy = "patient")
	private List<Appointment> appointmentList;

	public Patient() {
		// Default Constructor
	}

	public Patient(String patientBloodGroup, int patientAge, String patientLocation, String patientGender,
			String patientPhone, byte[] patientImage) {
		this.patientBloodGroup = patientBloodGroup;
		this.patientAge = patientAge;
		this.patientLocation = patientLocation;
		this.patientGender = patientGender;
		this.patientPhone = patientPhone;
		this.patientImage = patientImage;
	}

	public Patient(String patientBloodGroup, int patientAge, String patientLocation, String patientGender,
			String patientPhone) {
		this.patientBloodGroup = patientBloodGroup;
		this.patientAge = patientAge;
		this.patientLocation = patientLocation;
		this.patientGender = patientGender;
		this.patientPhone = patientPhone;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
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

	public GenericUser getUser() {
		return user;
	}

	public void setUser(GenericUser user) {
		this.user = user;
	}

	public List<Appointment> getAppointmentList() {
		return appointmentList;
	}

	public void setAppointmentList(List<Appointment> appointmentList) {
		this.appointmentList = appointmentList;
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

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", patientBloodGroup=" + patientBloodGroup + ", patientAge="
				+ patientAge + ", patientLocation=" + patientLocation + ", patientGender=" + patientGender
				+ ", patientPhone=" + patientPhone + "]";
	}

}
