package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "feedback")
public class Feedback {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "feedback_id")
	private Integer feedbackId;

	@NotNull(message = "Feedback Content cannot be Null")
	@Column(name = "feedback_content", length = 1000)
	private String feedbackContent;

	@NotNull(message = "Feedback Rating cannot be Null")
	@Column(name = "feedback_rating")
	private int feedbackRating;

	public Feedback() {
		// Default Constructor
	}

	public Feedback(String feedbackContent, int feedbackRating) {
		this.feedbackContent = feedbackContent;
		this.feedbackRating = feedbackRating;
	}

	public Integer getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(Integer feedbackId) {
		this.feedbackId = feedbackId;
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

	@Override
	public String toString() {
		return "Feedback [feedbackId=" + feedbackId + ", feedbackContent=" + feedbackContent + ", feedbackRating="
				+ feedbackRating + "]";
	}
}
