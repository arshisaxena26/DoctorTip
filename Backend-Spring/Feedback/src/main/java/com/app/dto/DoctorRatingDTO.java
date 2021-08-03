package com.app.dto;

import java.util.List;

public class DoctorRatingDTO {
	private List<FeedbackDTO> listOfFeedback;

	public DoctorRatingDTO() {
		// Default Constructor
	}

	public DoctorRatingDTO(List<FeedbackDTO> listOfFeedback) {
		this.listOfFeedback = listOfFeedback;
	}

	public List<FeedbackDTO> getListOfFeedback() {
		return listOfFeedback;
	}

	public void setListOfFeedback(List<FeedbackDTO> listOfFeedback) {
		this.listOfFeedback = listOfFeedback;
	}

}
