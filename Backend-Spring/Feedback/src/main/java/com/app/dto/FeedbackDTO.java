package com.app.dto;

public class FeedbackDTO {
	private Integer id;
	private Integer feedbackRating;

	public FeedbackDTO() {
		// Default Constructor
	}

	public FeedbackDTO(Integer id, Integer feedbackRating) {
		this.id = id;
		this.feedbackRating = feedbackRating;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFeedbackRating() {
		return feedbackRating;
	}

	public void setFeedbackRating(Integer feedbackRating) {
		this.feedbackRating = feedbackRating;
	}
}
