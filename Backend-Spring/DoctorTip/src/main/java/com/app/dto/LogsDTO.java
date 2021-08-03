package com.app.dto;

import java.time.LocalDateTime;

public class LogsDTO {

	private String log;
	private LocalDateTime date;

	public LogsDTO() {
		// Default Constructor
	}

	public LogsDTO(String log, LocalDateTime date) {
		this.log = log;
		this.date = date;
	}

	public String getLog() {
		return log;
	}

	public LocalDateTime getDate() {
		return date;
	}
}
