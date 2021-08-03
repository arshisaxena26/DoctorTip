package com.app.dto;

public class ConcernDTO {

	private String concernComment;

	private int appointmentId;

	public ConcernDTO() {
		// Default Constructor
	}

	public ConcernDTO(String concernComment, int appointmentId) {
		super();
		this.concernComment = concernComment;
		this.appointmentId = appointmentId;
	}

	public String getConcernComment() {
		return concernComment;
	}

	public void setConcernComment(String concernComment) {
		this.concernComment = concernComment;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}
}
