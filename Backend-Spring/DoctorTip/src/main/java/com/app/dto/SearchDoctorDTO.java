package com.app.dto;

public class SearchDoctorDTO {

	private String doctorSpecialization;
	private String appointmentDate;
	private String doctorName;

	public SearchDoctorDTO() {
		// Default Constructor
	}

	public SearchDoctorDTO(String doctorSpecialization, String appointmentDate, String doctorName) {
		this.doctorSpecialization = doctorSpecialization;
		this.appointmentDate = appointmentDate;
		this.doctorName = doctorName;
	}

	public String getDoctorSpecialization() {
		return doctorSpecialization;
	}

	public void setDoctorSpecialization(String doctorSpecialization) {
		this.doctorSpecialization = doctorSpecialization;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	@Override
	public String toString() {
		return "SearchDoctorDTO [doctorSpecialization=" + doctorSpecialization + ", appointmentDate=" + appointmentDate
				+ ", doctorName=" + doctorName + "]";
	}

}
