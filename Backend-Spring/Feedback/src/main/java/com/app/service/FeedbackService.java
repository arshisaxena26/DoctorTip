package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Feedback;
import com.app.repository.FeedbackRepository;

@Service
@Transactional
public class FeedbackService {

	@Autowired
	private FeedbackRepository feedbackRepository;

	public Integer saveFeedback(String feedbackContent, Integer feedbackRating) {

		Feedback feedback = new Feedback(feedbackContent, feedbackRating);
		feedbackRepository.save(feedback);
		return feedback.getFeedbackId();
	}

	public Feedback findFeedbackById(int feedbackId) {

		return feedbackRepository.findById(feedbackId).get();
	}

	public List<Feedback> getAllFeedbacks() {
		return feedbackRepository.findAll();
	}

}
