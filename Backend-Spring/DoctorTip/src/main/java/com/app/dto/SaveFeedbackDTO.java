package com.app.dto;

public class SaveFeedbackDTO {
	private Integer id = 0;
	private String feedbackContent;
	private Integer feedbackRating;

	public SaveFeedbackDTO() {
		// Default Constructor
	}

	public SaveFeedbackDTO(String feedbackContent, int feedbackRating) {
		this.feedbackContent = feedbackContent;
		this.feedbackRating = feedbackRating;
	}

	public String getFeedbackContent() {
		return feedbackContent;
	}

	public void setFeedbackContent(String feedbackContent) {
		this.feedbackContent = feedbackContent;
	}

	public int getFeedbackRating() {
		return feedbackRating;
	}

	public void setFeedbackRating(int feedbackRating) {
		this.feedbackRating = feedbackRating;
	}

	public Integer getId() {
		return id;
	}
}