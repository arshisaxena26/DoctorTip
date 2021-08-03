package com.app.dto;

public class StatsDTO {

	private String label;
	private long count;

	public StatsDTO() {
		// Default Constructor
	}

	public StatsDTO(String label, long count) {
		this.label = label;
		this.count = count;
	}

	public String getLabel() {
		return label;
	}

	public long getCount() {
		return count;
	}
}
