package com.app.dto;

public class DoctorStatsDTO {

	private String name;
	private int likes;
	private int appointments;
	private int concerns;
	private int warnings;

	public DoctorStatsDTO() {
		// Default Constructor
	}

	public DoctorStatsDTO(String name, int likes, int appointments, int concerns, int warnings) {
		this.name = name;
		this.likes = likes;
		this.appointments = appointments;
		this.concerns = concerns;
		this.warnings = warnings;
	}

	public String getName() {
		return name;
	}

	public int getLikes() {
		return likes;
	}

	public int getAppointments() {
		return appointments;
	}

	public int getConcerns() {
		return concerns;
	}

	public int getWarnings() {
		return warnings;
	}
}
