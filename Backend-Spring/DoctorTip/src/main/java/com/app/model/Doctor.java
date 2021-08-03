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
@Table(name = "doctor")
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "doctor_id")
	private Integer doctorId;

	@NotNull(message = "Doctor Specialization cannot be Null")
	@Column(name = "doctor_specialization", length = 50)
	private String doctorSpecialization;

	@NotNull(message = "Doctor Experience cannot be Null")
	@Column(name = "doctor_experience")
	private int doctorExperience;

	@NotNull(message = "Doctor Location cannot be Null")
	@Column(name = "doctor_location", length = 30)
	private String doctorLocation;

	@NotNull(message = "Doctor Clinic Name cannot be Null")
	@Column(name = "doctor_clinic_name", length = 50)
	private String doctorClinicName;

	@NotNull(message = "Doctor Consultation Fees cannot be Null")
	@Column(name = "doctor_consultation_fee")
	private double doctorConsultationFee;

	@Nullable
	@Column(name = "doctor_gender", length = 20)
	private String doctorGender;

	@Nullable
	@Column(name = "doctor_phone", length = 15)
	private String doctorPhone;

	@Nullable
	@Lob
	@Column(name = "doctor_image", length = 150)
	private byte[] doctorImage;

	@OneToOne(targetEntity = GenericUser.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private GenericUser user;

	@OneToMany(mappedBy = "doctor")
	private List<Appointment> appointmentList;

//	@OneToMany
//	@JoinColumn(name = "doctor_availablity_id")
//	private List<DoctorAvailablity> availablity;
	
	@OneToMany(mappedBy = "doctor")
	private List<DoctorAvailablity> availablity;

	public Doctor() {
		
	}

	public Doctor(String doctorSpecialization, int doctorExperience, String doctorLocation, String doctorClinicName,
			double doctorConsultationFee, String doctorGender, String doctorPhone, byte[] doctorImage) {
		this.doctorSpecialization = doctorSpecialization;
		this.doctorExperience = doctorExperience;
		this.doctorLocation = doctorLocation;
		this.doctorClinicName = doctorClinicName;
		this.doctorConsultationFee = doctorConsultationFee;
		this.doctorGender = doctorGender;
		this.doctorPhone = doctorPhone;
		this.doctorImage = doctorImage;
	}
	

	public Doctor(Integer doctorId,String doctorSpecialization, GenericUser user) {
		this.doctorId = doctorId;
		this.doctorSpecialization = doctorSpecialization;
		this.user = user;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
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

	public List<DoctorAvailablity> getAvailablity() {
		return availablity;
	}

	public void setAvailablity(List<DoctorAvailablity> availablity) {
		this.availablity = availablity;
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

	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", doctorSpecialization=" + doctorSpecialization + ", doctorExperience="
				+ doctorExperience + ", doctorLocation=" + doctorLocation + ", doctorClinicName=" + doctorClinicName
				+ ", doctorConsultationFee=" + doctorConsultationFee + ", doctorGender=" + doctorGender
				+ ", doctorPhone=" + doctorPhone + "]";
	}

}
