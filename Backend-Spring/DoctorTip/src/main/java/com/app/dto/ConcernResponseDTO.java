package com.app.dto;

import java.time.LocalDate;

public class ConcernResponseDTO {
	private Integer id;
	private String comment;
	private String name;
	private LocalDate date;
	private boolean isWarned;
	private boolean isBlocked;

	public ConcernResponseDTO() {
		// Default Constructor
	}

	public ConcernResponseDTO(Integer id, String comment, String name, LocalDate date, boolean isWarned,
			boolean isBlocked) {
		this.id = id;
		this.comment = comment;
		this.name = name;
		this.date = date;
		this.isWarned = isWarned;
		this.isBlocked = isBlocked;
	}

	public Integer getId() {
		return id;
	}

	public String getComment() {
		return comment;
	}

	public String getName() {
		return name;
	}

	public LocalDate getDate() {
		return date;
	}

	public boolean isWarned() {
		return isWarned;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

}
