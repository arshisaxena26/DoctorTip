package com.app.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AppointmentDTO;
import com.app.dto.ConcernResponseDTO;
import com.app.dto.DoctorDTO;
import com.app.dto.DoctorListDTO;
import com.app.model.Comment;
import com.app.model.Doctor;
import com.app.model.DoctorAvailablity;
import com.app.model.Prescription;
import com.app.service.DoctorService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/doctor")
public class DoctorController {

	Logger logger = LogManager.getLogger("DoctorController.class");

	@Autowired
	DoctorService doctorService;

	@GetMapping("/viewprofile")
	public ResponseEntity<DoctorListDTO> getDoctor(HttpServletRequest request) {
		try {
			Principal principal = request.getUserPrincipal();
			String email = principal.getName();
			Doctor doctor = doctorService.getDoctor(email);
			DoctorListDTO doctorDTO = new DoctorListDTO(doctor.getDoctorId(), doctor.getDoctorSpecialization(),
					doctor.getDoctorClinicName(), doctor.getDoctorConsultationFee(), doctor.getDoctorExperience(),
					doctor.getDoctorImage(), doctor.getDoctorLocation(), doctor.getUser().getUserName(),
					doctorService.getDoctorComments(doctor.getDoctorId()), doctor.getAvailablity());

			return new ResponseEntity<>(doctorDTO, HttpStatus.OK);
		} catch (Exception e) {
			logger.warn(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getAppointment")
	public List<AppointmentDTO> getAppointment(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String email = principal.getName();
		try {
			return doctorService.getAppointment(email);
		} catch (Exception e) {
			logger.warn(e);
			return null;
		}
	}

	@PostMapping("/reply/{commentId}")
	public ResponseEntity<Comment> setReply(@PathVariable Integer commentId, @RequestBody String comment) {
		try {
			return new ResponseEntity<>(doctorService.setReply(commentId, comment), HttpStatus.OK);
		} catch (Exception e) {
			logger.warn(e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/sendPrescription/{appointmentId}")
	public ResponseEntity<Void> sendPrescription(@PathVariable Integer appointmentId,
			@RequestBody Prescription prescription) {
		try {
			doctorService.sendPrescription(appointmentId, prescription);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.warn(e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/setAvailability/{id}")
	public ResponseEntity<Void> updateDoctorDate(@PathVariable Integer id, @RequestBody DoctorAvailablity doctorAvail) {
		try {
			doctorService.updateDoctorDate(id, doctorAvail);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.warn(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/deleteAvailability/{id}")
	public ResponseEntity<Void> deleteDoctor(@PathVariable int id) {
		try {
			doctorService.deleteDoctorDate(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.warn(e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/updateAppointmentStatus/{id}")
	public ResponseEntity<Void> updateAppointment(@PathVariable int id) {
		try {
			doctorService.updateAppointmentStatus(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.warn(e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/like/{id}")
	public ResponseEntity<Comment> setlike(@PathVariable Integer id) {
		try {
			return new ResponseEntity<>(doctorService.setLike(id), HttpStatus.OK);
		} catch (Exception e) {
			logger.warn(e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/concerns/{email}")
	public ResponseEntity<List<ConcernResponseDTO>> getDoctorsConcerns(@PathVariable String email) {
		try {
			return new ResponseEntity<>(doctorService.getDoctorConcern(email), HttpStatus.OK);
		} catch (Exception e) {
			logger.warn(e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/savedoctorprofile", method = RequestMethod.POST)
	public ResponseEntity<Void> saveDoctorProfile(@ModelAttribute DoctorDTO doctorProfile) {
		try {
			doctorService.saveDoctorProfile(doctorProfile);
			logger.info("Doctor profile has been store into the database");
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.warn(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
