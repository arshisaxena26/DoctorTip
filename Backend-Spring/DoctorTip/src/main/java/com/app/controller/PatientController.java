package com.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AppointmentDTO;
import com.app.dto.AppointmentResponseDTO;
import com.app.dto.CommentDTO;
import com.app.dto.ConcernDTO;
import com.app.dto.DoctorResponseDTO;
import com.app.dto.LikeDislikeDTO;
import com.app.dto.PatientDTO;
import com.app.dto.PatientResponseDTO;
import com.app.dto.PrescriptionDTO;
import com.app.dto.SaveFeedbackDTO;
import com.app.dto.SearchDoctorDTO;
import com.app.dto.SearchDoctorResponseDTO;
import com.app.dto.ShowCommentDTO;
import com.app.model.Appointment;
import com.app.model.Prescription;
import com.app.model.UserActivity;
import com.app.service.PatientService;
import com.fasterxml.jackson.annotation.JsonBackReference;

@CrossOrigin
@RestController
@RequestMapping("/patient")
public class PatientController {
	Logger logger = LogManager.getLogger("PatientController.class");
	@Autowired
	PatientService patientService;

	@JsonBackReference
	private UserActivity user;

	@RequestMapping(value = "/savepatientprofile", method = RequestMethod.POST)
	public ResponseEntity<Void> savePatientProfile(@ModelAttribute PatientDTO patientProfile) {
		try {
			patientService.savePatientProfile(patientProfile);
			logger.info("Patient profile has been store into the database");
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.warn(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getspecializations", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getDoctorSpecialization() {
		try {
			logger.info("Doctor specialization is fetch from the database");
			return new ResponseEntity<>(patientService.getSpecialization(), HttpStatus.OK);
		} catch (Exception e) {
			logger.warn(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/listofdoctor", method = RequestMethod.POST)
	public ResponseEntity<List<SearchDoctorResponseDTO>> getDoctorListForDisplay(
			@ModelAttribute SearchDoctorDTO searchdoc) {
		try {
			logger.info("List of Doctor fetch from into the database");
			return new ResponseEntity<>(patientService.getDoctorListForDisplay(searchdoc), HttpStatus.OK);
		} catch (Exception e) {
			logger.warn(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/makeappointments")
	public ResponseEntity<Map<Integer, String>> fetchAvailableTimeSlot(@RequestParam String date,
			@RequestParam Integer id) {
		try {
			return new ResponseEntity<>(patientService.fetchAvailableTimeSlot(date, id), HttpStatus.OK);
		} catch (Exception e) {
			logger.warn(e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/submitappointment")
	public ResponseEntity<Void> submitAppointment(@RequestBody AppointmentDTO appointmentDTO) {
		try {
			patientService.submitAppointment(appointmentDTO);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.warn(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/concerns")
	public ResponseEntity<Void> fillConcern(@RequestBody ConcernDTO concernDTO) {
		try {
			patientService.fillConcern(concernDTO);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.warn(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/prescriptions")
	public ResponseEntity<PrescriptionDTO> showPrescription(@RequestParam Integer appointmentId) {
		try {
			Prescription prescription = patientService.showPrescription(appointmentId);
			return new ResponseEntity<>(new PrescriptionDTO(prescription.getPrescriptionContent(),
					prescription.getDoS(), prescription.getDontS(), prescription.getFollowUp().toString()),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.warn(e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/patientappointmentlist")
	public ResponseEntity<List<AppointmentResponseDTO>> appointmentListOfPatient(@RequestParam Integer patientId) {
		try {
			List<Appointment> appointmentforpatient = patientService.appointmentListOfPatient(patientId);
			List<AppointmentResponseDTO> appointmentListDTO = new ArrayList<>();

			appointmentforpatient.forEach(
					appointment -> appointmentListDTO.add(new AppointmentResponseDTO(appointment.getAppointmentId(),
							appointment.getAppointmentDate().toString(), appointment.getAppointmentStatus(),
							appointment.getDoctor().getUser().getUserName(),
							appointment.getDoctor().getDoctorClinicName(), appointment.getTimeSlot().getSlot(),
							(appointment.getFeedbackId() != null) ? appointment.getFeedbackId() : 0,
							(appointment.getConcern() != null) ? appointment.getConcern().getConcernId() : 0)));

			return new ResponseEntity<>(appointmentListDTO, HttpStatus.OK);
		} catch (Exception e) {
			logger.warn(e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/docprofile/{id}")
	public ResponseEntity<DoctorResponseDTO> getDocProfile(@PathVariable Integer id) {
		try {
			logger.info("getting doctor profile from user id saved in session");
			return new ResponseEntity<>(patientService.getDocById(id), HttpStatus.OK);
		} catch (RuntimeException e) {
			logger.warn(e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/patprofile/{id}")
	public ResponseEntity<PatientResponseDTO> getPatProfile(@PathVariable Integer id) {
		try {
			logger.info("getting patient profile from user id saved in session");
			return new ResponseEntity<>(patientService.getPatByUserId(id), HttpStatus.OK);
		} catch (RuntimeException e) {
			logger.warn(e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/getlikedislike/{docId}/{userId}", method = RequestMethod.GET)
	public ResponseEntity<LikeDislikeDTO> getlikedislike(@PathVariable Integer docId, @PathVariable Integer userId) {
		try {
			logger.info("getting like dislike from database");
			return new ResponseEntity<>(patientService.getlikedislike(docId, userId), HttpStatus.OK);
		} catch (Exception e) {
			logger.warn(e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/getlikecount/{docId}", method = RequestMethod.GET)
	public ResponseEntity<Integer> getlikecount(@PathVariable Integer docId) {
		try {
			logger.info("getting like count from database");
			return new ResponseEntity<>(patientService.getlikecount(docId), HttpStatus.OK);
		} catch (Exception e) {
			logger.warn(e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/getdislikecount/{docId}", method = RequestMethod.GET)
	public ResponseEntity<Integer> getdislikecount(@PathVariable Integer docId) {
		try {
			logger.info("getting dislike count from database");
			return new ResponseEntity<>(patientService.getdislikecount(docId), HttpStatus.OK);
		} catch (Exception e) {
			logger.warn(e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/setlikedislike", method = RequestMethod.POST)
	public ResponseEntity<Void> dislikeDoctor(@RequestBody LikeDislikeDTO likeDislikeDto) {
		try {
			patientService.setLikeDislike(likeDislikeDto);
			logger.info("Setting dislike and like for doctor along with creating like and dislike null initially");
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.warn(e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/comment/{docId}")
	public ResponseEntity<List<ShowCommentDTO>> getAllCommentofDoctor(@PathVariable Integer docId) {
		try {
			logger.info("show all comments on page for a specific doctor");
			return new ResponseEntity<>(patientService.getAllCommentofDoctor(docId), HttpStatus.OK);
		} catch (Exception e) {
			logger.warn(e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/savecomment", method = { RequestMethod.POST, RequestMethod.PUT })
	public ResponseEntity<Void> savecomment(@RequestBody CommentDTO commentDTO) {
		try {
			patientService.saveComment(commentDTO);
			logger.info("saving comment that is given to doctor by patient");
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.warn(e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/savefeed/{Appid}", method = RequestMethod.POST)
	public ResponseEntity<Void> saveFeed(@RequestBody SaveFeedbackDTO feedbackDTO,
			@PathVariable("Appid") Integer appointmentId) {
		try {
			patientService.saveFeedback(feedbackDTO, appointmentId);
			logger.info("Feed controller to hit feedback microservice using rest template");
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.warn(e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
