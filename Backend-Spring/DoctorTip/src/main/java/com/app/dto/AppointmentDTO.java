package com.app.dto;

public class AppointmentDTO {
	private int patientUserId;
	private int doctorId;
	private String appointmentDate;
	private int timeSlot;
	private String appointmentStatus;
	private String doctorClinicName;
	private String timeslottime;
	private int appointmentId;
	private String patientName;
	private int prescription;

	public AppointmentDTO() {
		// Default Constructor
	}

	public AppointmentDTO(int patientUserId, int doctorId, String appointmentDate, int timeSlot) {
		this.patientUserId = patientUserId;
		this.doctorId = doctorId;
		this.appointmentDate = appointmentDate;
		this.timeSlot = timeSlot;
	}

	public AppointmentDTO(String appointmentDate, String appointmentStatus, String timeslottime, int appointmentId,
			String patientName, int prescription) {
		this.appointmentDate = appointmentDate;
		this.appointmentStatus = appointmentStatus;
		this.timeslottime = timeslottime;
		this.appointmentId = appointmentId;
		this.patientName = patientName;
		this.prescription = prescription;
	}

	public int getPatientUserId() {
		return patientUserId;
	}

	public void setPatientUserId(int patientUserId) {
		this.patientUserId = patientUserId;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getDoctorClinicName() {
		return doctorClinicName;
	}

	public void setDoctorName(String doctorClinicName) {
		this.doctorClinicName = doctorClinicName;
	}

	public int getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(int timeSlot) {
		this.timeSlot = timeSlot;
	}

	public String getAppointmentStatus() {
		return appointmentStatus;
	}

	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

	public String getTimeslottime() {
		return timeslottime;
	}

	public void setTimeslottime(String timeslottime) {
		this.timeslottime = timeslottime;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public String getPatientName() {
		return patientName;
	}

	public int getPrescription() {
		return prescription;
	}
}
