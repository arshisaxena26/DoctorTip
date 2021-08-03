package com.app.dto;

public class AppointmentResponseDTO {
	private int id;
	private String date;
	private String status;
	private String drName;
	private String clinicName;
	private String timeSlot;
	private int feedbackId;
	private int concernId;

	public AppointmentResponseDTO() {
		// Default Constructor
	}

	public AppointmentResponseDTO(int id, String date, String status, String drName, String clinicName, String timeSlot,
			int feedbackId, int concernId) {
		super();
		this.id = id;
		this.date = date;
		this.status = status;
		this.drName = drName;
		this.clinicName = clinicName;
		this.timeSlot = timeSlot;
		this.feedbackId = feedbackId;
		this.concernId = concernId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDrName() {
		return drName;
	}

	public void setDrName(String drName) {
		this.drName = drName;
	}

	public String getClinicName() {
		return clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}

	public String getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}

	public int getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}

	public int getConcernId() {
		return concernId;
	}

	public void setConcernId(int concernId) {
		this.concernId = concernId;
	}
}
