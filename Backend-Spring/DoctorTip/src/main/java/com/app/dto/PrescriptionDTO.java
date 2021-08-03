package com.app.dto;

public class PrescriptionDTO {
	private String message;
	private String dos;
	private String donts;
	private String followUp;

	public PrescriptionDTO() {
		// Default Constructor
	}

	public PrescriptionDTO(String message, String dos, String donts, String followUp) {
		super();
		this.message = message;
		this.dos = dos;
		this.donts = donts;
		this.followUp = followUp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDos() {
		return dos;
	}

	public void setDos(String dos) {
		this.dos = dos;
	}

	public String getDonts() {
		return donts;
	}

	public void setDonts(String donts) {
		this.donts = donts;
	}

	public String getFollowUp() {
		return followUp;
	}

	public void setFollowUp(String followUp) {
		this.followUp = followUp;
	}

}
