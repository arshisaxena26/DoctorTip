package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.DoctorRatingDTO;
import com.app.dto.FeedbackDTO;
import com.app.model.Feedback;
import com.app.service.FeedbackService;

@CrossOrigin
@RestController
@RequestMapping("/feedback")
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;

	@RequestMapping(value = "/savefeedback/{content}/{rating}", method = { RequestMethod.GET, RequestMethod.PUT,
			RequestMethod.POST })
	public ResponseEntity<Integer> saveFeedback(@PathVariable("content") String feedbackContent,
			@PathVariable("rating") Integer feedbackRating) {
		Integer id = feedbackService.saveFeedback(feedbackContent, feedbackRating);
		return new ResponseEntity<>(id, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public Feedback findFeedbackById(@PathVariable("id") int feedbackId) {
		return feedbackService.findFeedbackById(feedbackId);
	}

	@RequestMapping(value = "/getfeedbacks", method = RequestMethod.GET)
	public ResponseEntity<DoctorRatingDTO> getAllFeedbacks() {
		List<Feedback> feedbackList = feedbackService.getAllFeedbacks();
		List<FeedbackDTO> feedbackDTO = new ArrayList<>();
		feedbackList.forEach(item -> feedbackDTO.add(new FeedbackDTO(item.getFeedbackId(), item.getFeedbackRating())));
		return new ResponseEntity<>(new DoctorRatingDTO(feedbackDTO), HttpStatus.OK);
	}
}
