package com.app.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.dto.AppointmentDTO;
import com.app.dto.CommentDTO;
import com.app.dto.ConcernDTO;
import com.app.dto.DoctorRatingDTO;
import com.app.dto.DoctorResponseDTO;
import com.app.dto.FeedbackDTO;
import com.app.dto.LikeDislikeDTO;
import com.app.dto.PatientDTO;
import com.app.dto.PatientResponseDTO;
import com.app.dto.SaveFeedbackDTO;
import com.app.dto.SearchDoctorDTO;
import com.app.dto.SearchDoctorResponseDTO;
import com.app.dto.ShowCommentDTO;
import com.app.model.Appointment;
import com.app.model.Comment;
import com.app.model.Concern;
import com.app.model.Doctor;
import com.app.model.GenericUser;
import com.app.model.Likes;
import com.app.model.Patient;
import com.app.model.Prescription;
import com.app.model.TimeSlot;
import com.app.repository.AppointmentRepository;
import com.app.repository.CommentRepository;
import com.app.repository.ConcernRepository;
import com.app.repository.DoctorRepository;
import com.app.repository.LikesRepository;
import com.app.repository.PatientRepository;
import com.app.repository.PrescriptionRepository;
import com.app.repository.TimeSlotRepository;
import com.app.repository.UserRepository;

@Service
@Transactional
public class PatientService {
	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TimeSlotRepository timeSlotRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private ConcernRepository concernRepository;

	@Autowired
	private PrescriptionRepository prescriptionRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private LikesRepository likesRepository;

	public Patient savePatientProfile(PatientDTO patient) throws IOException {
		// creating doctor object and store it in database
		Patient patientObj = new Patient(patient.getPatientBloodGroup(), patient.getPatientAge(),
				patient.getPatientLocation(), patient.getPatientGender(), patient.getPatientPhone(),
				patient.getPatientImage().getBytes());
		GenericUser user = userRepository.getById(patient.getUserId());
		patientObj.setUser(user);
		return patientRepository.save(patientObj);
	}

	public List<String> getSpecialization() {
		return doctorRepository.findDistinctSpecialization();
	}

	public List<SearchDoctorResponseDTO> getDoctorListForDisplay(SearchDoctorDTO searchdoc) {

		// convert appointment string into LocalDate
		List<Doctor> listOfDoctors = doctorRepository
				.searchDoctorByAvailablity(LocalDate.parse(searchdoc.getAppointmentDate()));

		RestTemplate restTemp = new RestTemplate();
		DoctorRatingDTO doctorRating = restTemp.getForObject("http://localhost:9090/feedback/getfeedbacks",
				DoctorRatingDTO.class);

		List<FeedbackDTO> listOfFeedback = doctorRating.getListOfFeedback();

		// Filter out the doctor list base on specialization or doctor name
		List<Doctor> searchResult = listOfDoctors.stream()
				.filter(doctorObj -> doctorObj.getDoctorSpecialization().equals(searchdoc.getDoctorSpecialization())
						|| doctorObj.getUser().getUserName().equals(searchdoc.getDoctorName()))
				.collect(Collectors.toList());

		List<SearchDoctorResponseDTO> listOfDoctorResponse = new ArrayList<>();
		searchResult.forEach(doctorObj -> listOfDoctorResponse.add(new SearchDoctorResponseDTO(doctorObj.getDoctorId(),
				doctorObj.getDoctorSpecialization(), doctorObj.getUser().getUserName(), doctorObj.getDoctorExperience(),
				doctorObj.getDoctorLocation(), doctorObj.getDoctorClinicName(), doctorObj.getDoctorConsultationFee(),
				doctorObj.getDoctorPhone(), doctorObj.getDoctorImage(),
				getRatings(doctorObj.getAppointmentList(), listOfFeedback))));

		return listOfDoctorResponse;
	}

	public int getRatings(List<Appointment> appointmentList, List<FeedbackDTO> listOfFeedback) {
		int sumOfRating = 0;
		int counter = 0;

		for (Appointment appointment : appointmentList) {
			for (FeedbackDTO feedback : listOfFeedback) {
				if (feedback.getId() == appointment.getFeedbackId()) {
					sumOfRating += feedback.getFeedbackRating();
					counter++;
				}
			}
		}
		if (counter == 0)
			return sumOfRating;
		return sumOfRating / counter;
	}

	public Map<Integer, String> fetchAvailableTimeSlot(String date, int id) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(date, formatter);
		List<Appointment> bookedAppointmentList = appointmentRepository.fetchBookedTimeslot(localDate, id);
		List<TimeSlot> allTimeSlot = timeSlotRepository.findAll();
		Map<Integer, String> availableTimeSlot = new HashMap<>();

		for (TimeSlot timeSlot : allTimeSlot) {
			availableTimeSlot.put(timeSlot.getTimeSlotId(), timeSlot.getSlot());
		}
		for (Appointment appointment : bookedAppointmentList) {
			if (availableTimeSlot.containsKey(appointment.getTimeSlot().getTimeSlotId())) {
				availableTimeSlot.remove(appointment.getTimeSlot().getTimeSlotId());
			}
		}
		return availableTimeSlot;
	}

	public Appointment submitAppointment(AppointmentDTO appointmentDTO) {

		LocalDate localDate = LocalDate.parse(appointmentDTO.getAppointmentDate());
		TimeSlot timeSlot = timeSlotRepository.getById(appointmentDTO.getTimeSlot());
		Doctor dr = doctorRepository.getById(appointmentDTO.getDoctorId());
		Patient patient = patientRepository.getByUserId(appointmentDTO.getPatientUserId());

		Appointment appointment = new Appointment(localDate, "Booked", null, patient, dr, timeSlot);
		return appointmentRepository.save(appointment);

	}

	public Concern fillConcern(ConcernDTO concernDTO) {

		Appointment appointment = appointmentRepository.getById(concernDTO.getAppointmentId());
		Concern concern = new Concern();
		concern.setAppointment(appointment);
		concern.setConcernComment(concernDTO.getConcernComment());
		return concernRepository.save(concern);
	}

	public Prescription showPrescription(Integer appointmentId) {

		return prescriptionRepository.checkPrescription(appointmentId);

	}

	public List<Appointment> appointmentListOfPatient(Integer patientId) {
		return appointmentRepository.listOfAppointment(patientId);

	}

	// Extract a Doctor for specific doctor ID
	public DoctorResponseDTO getDocById(int id) {
		Doctor doc = doctorRepository.findById(id).get();
		return new DoctorResponseDTO(doc.getDoctorId(), doc.getUser().getUserName(), doc.getUser().getUserEmail(),
				doc.getDoctorSpecialization(), doc.getDoctorExperience(), doc.getDoctorLocation(),
				doc.getDoctorClinicName(), doc.getDoctorConsultationFee(), doc.getDoctorGender(), doc.getDoctorPhone(),
				doc.getDoctorImage());
	}

	// Extract a Patient for specific user ID
	public PatientResponseDTO getPatByUserId(int id) {
		Patient pat = patientRepository.findByUserId(id);
		return new PatientResponseDTO(pat.getPatientId(), pat.getPatientBloodGroup(), pat.getPatientAge(),
				pat.getPatientLocation(), pat.getPatientGender(), pat.getPatientPhone(), pat.getPatientImage(),
				pat.getUser().getUserName());
	}

	// Extract all comments for a doctor on doctor profile for patient
	public List<ShowCommentDTO> getAllCommentofDoctor(int docId) {

		List<Comment> comments = commentRepository.findByDoctor(docId);
		List<ShowCommentDTO> commentslist = new ArrayList<>();
		for (Comment comment : comments) {
			commentslist.add(new ShowCommentDTO(comment.getCommentId(), comment.getCommentContent(),
					comment.getCommentReply(), comment.isLiked(), comment.getPatient().getUser().getUserName(),
					comment.getPatient().getPatientImage()));
		}
		return commentslist;
	}

	// Save a Comment in Db for a specific doctor by a specific user
	public boolean saveComment(CommentDTO commentDTO) {
		Comment commentObj = new Comment(commentDTO.getCommentContent(), null, false);
		commentObj.setDoctor(doctorRepository.getById(commentDTO.getDocId()));
		commentObj.setPatient(patientRepository.findByUserId(commentDTO.getUserId()));
		commentRepository.save(commentObj);
		return true;
	}

	// set like and dislike of doc by specific pat
	public Likes setLikeDislike(LikeDislikeDTO likeDislikeDto) {

		Likes likeObj = likesRepository.findByDocIdandPatId(likeDislikeDto.getDocId(), likeDislikeDto.getPatId());
		likeObj.setDisliked(likeDislikeDto.isDislike());
		likeObj.setLiked(likeDislikeDto.isLike());
		likesRepository.save(likeObj);
		return likeObj;
	}

	// saving feedback in microservice and as a return getting feedback id which we
	// save in appointment table of doctor tip schema
	public void saveFeedback(SaveFeedbackDTO feedbackDTO, Integer appointmentId) {

		RestTemplate restTemp = new RestTemplate();
		int id = restTemp.getForObject(
				"http://localhost:9090/feedback/savefeedback/" + feedbackDTO.getFeedbackContent() + "/"
						+ feedbackDTO.getFeedbackRating(),
				Integer.class, Integer.toString(feedbackDTO.getFeedbackRating()));

		Appointment app = appointmentRepository.getById(appointmentId);
		app.setFeedbackId(id);
		appointmentRepository.save(app);

	}

	// Extract like Dislike
	public LikeDislikeDTO getlikedislike(Integer docId, Integer userId) {

		Patient pat = patientRepository.findByUserId(userId);
		Doctor doc = doctorRepository.findById(docId).get();
		Likes likeDislike = likesRepository.findByDocIdandPatId(docId, pat.getPatientId());
		LikeDislikeDTO likeDislikeDto = null;
		if (likeDislike == null) {
			likeDislikeDto = new LikeDislikeDTO(false, false, docId, pat.getPatientId());

			likesRepository.save(new Likes(likeDislikeDto.isDislike(), likeDislikeDto.isLike(), doc, pat));
		} else {
			likeDislikeDto = new LikeDislikeDTO(likeDislike.isDisliked(), likeDislike.isLiked(),
					likeDislike.getDoctor().getDoctorId(), likeDislike.getPatient().getPatientId());
		}

		return likeDislikeDto;

	}

//Extract Like count
	public Integer getlikecount(Integer docId) {
		Integer count;
		count = likesRepository.countLikes(docId);
		return count;
	}

	// Extract Dislike Count
	public Integer getdislikecount(Integer docId) {
		Integer count;
		count = likesRepository.countDisikes(docId);
		return count;
	}

}
