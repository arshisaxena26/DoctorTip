package com.app.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.app.dto.ConcernResponseDTO;
import com.app.dto.DoctorListDTO;
import com.app.dto.DoctorRatingDTO;
import com.app.dto.DoctorStatsDTO;
import com.app.dto.FeedbackDTO;
import com.app.dto.LogsDTO;
import com.app.dto.PatientListDTO;
import com.app.dto.StatsDTO;
import com.app.model.Concern;
import com.app.model.Doctor;
import com.app.model.GenericUser;
import com.app.model.Patient;
import com.app.model.UserActivity;
import com.app.service.AdminService;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {
	Logger logger = LogManager.getLogger("AdminController.class");

	@Autowired
	private AdminService adminService;

	@GetMapping("/patients")
	public ResponseEntity<List<PatientListDTO>> getPatients() {

		List<Patient> patients = adminService.getPatients();
		List<PatientListDTO> patientDTO = new ArrayList<>();

		// Transferring required data to DTO
		patients.forEach(p -> patientDTO.add(new PatientListDTO(p.getPatientId(), p.getPatientGender(),
				p.getPatientImage(), p.getPatientLocation(), p.getUser().getUserName(), p.getPatientAge(),
				adminService.getPatientLastLoginMonth(p.getUser().getUserId()),
				adminService.getPatientLastLogin(p.getUser().getUserId()), p.getUser().isBlocked())));

		logger.info("Patients List Request by Admin Completed");
		return new ResponseEntity<>(patientDTO, HttpStatus.OK);

	}

	@PutMapping("/deactivate")
	public ResponseEntity<Void> deactivatePatient(@RequestBody Integer patientId) {
		try {
			adminService.deactivatePatient(patientId);
			logger.info("Deactivate Patient Request by Admin Completed");
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			logger.warn(e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/activate")
	public ResponseEntity<Void> activatePatient(@RequestBody Integer patientId) {
		try {
			adminService.activatePatient(patientId);
			logger.info("Activate Patient Request by Admin Completed");
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.warn(e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/doctors")
	public ResponseEntity<List<DoctorListDTO>> getDoctors() {

		List<Doctor> doctors = adminService.getDoctors();
		List<DoctorListDTO> doctorDTO = new ArrayList<>();

		// Transferring required data to DTO
		doctors.forEach(d -> doctorDTO.add(new DoctorListDTO(d.getDoctorId(), d.getDoctorSpecialization(),
				d.getDoctorClinicName(), d.getDoctorConsultationFee(), d.getDoctorExperience(), d.getDoctorImage(),
				d.getDoctorLocation(), d.getUser().getUserName())));

		logger.info("Doctors List Request by Admin Completed");
		return new ResponseEntity<>(doctorDTO, HttpStatus.OK);

	}

	@GetMapping("/concerns/{doctorId}")
	public ResponseEntity<List<ConcernResponseDTO>> getDoctorsConcerns(@PathVariable Integer doctorId) {

		List<Concern> concerns = adminService.getDoctorReports(doctorId);
		List<ConcernResponseDTO> concernDTO = new ArrayList<>();

		// Transferring required data to DTO
		concerns.forEach(c -> concernDTO.add(new ConcernResponseDTO(c.getConcernId(), c.getConcernComment(),
				c.getAppointment().getPatient().getUser().getUserName(), c.getAppointment().getAppointmentDate(),
				c.isWarned(), c.getAppointment().getDoctor().getUser().isBlocked())));

		logger.info("Doctor Concerns Request by Admin Completed");
		return new ResponseEntity<>(concernDTO, HttpStatus.OK);

	}

	@PutMapping("/warning")
	public ResponseEntity<Void> sendWarning(@RequestBody Integer concerId) {
		try {
			adminService.sendWarning(concerId);
			logger.info("Doctor Warning Request by Admin Completed");
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.warn(e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/block")
	public ResponseEntity<Void> blockDoctor(@RequestBody Integer doctorId) {
		try {
			adminService.blockDoctor(doctorId);
			logger.info("Doctor Block Request by Admin Completed");
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.warn(e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/unblock")
	public ResponseEntity<Void> unblockDoctor(@RequestBody Integer doctorId) {
		try {
			adminService.unblockDoctor(doctorId);
			logger.info("Doctor Unblock Request by Admin Completed");
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.warn(e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/users")
	public ResponseEntity<Map<Integer, String>> getUserNames() {

		List<GenericUser> users = adminService.getUsers();
		Map<Integer, String> userNames = new HashMap<>();

		// Transferring required data to Map
		users.forEach(user -> userNames.putIfAbsent(user.getUserId(), user.getUserName()));

		logger.info("User Names Request by Admin Completed");
		return new ResponseEntity<>(userNames, HttpStatus.OK);

	}

	@GetMapping("/logs/{userId}")
	public ResponseEntity<List<LogsDTO>> getUserLogs(@PathVariable Integer userId) {

		List<UserActivity> activityLogs = adminService.getLogs(userId);
		List<LogsDTO> logs = new ArrayList<>();

		// Transferring required data to DTO
		activityLogs.forEach(log -> logs.add(new LogsDTO(log.getActivityContent(), log.getActivityTime())));

		logger.info("User Logs Request by Admin Completed");
		return new ResponseEntity<>(logs, HttpStatus.OK);

	}

	@GetMapping("/stats")
	public ResponseEntity<Map<String, Integer>> getStats() {

		Map<String, Integer> stats = new HashMap<>();

		stats.putIfAbsent("Total Appointments", adminService.getAppointments());
		stats.putIfAbsent("Total Users", adminService.getUsersCount() - 1);
		stats.putIfAbsent("Returning Users", adminService.getReturningUsers());
		stats.putIfAbsent("Total Concerns", adminService.getConcerns());

		logger.info("Statistics Request by Admin Completed");
		return new ResponseEntity<>(stats, HttpStatus.OK);

	}

	@GetMapping("/doctorStats")
	public ResponseEntity<List<DoctorStatsDTO>> getDoctorStats() {

		List<DoctorStatsDTO> stats = new ArrayList<>();
		List<Doctor> doctors = adminService.getDoctors();

		// Transferring required data to DTO
		doctors.forEach(d -> stats
				.add(new DoctorStatsDTO(d.getUser().getUserName(), adminService.getDoctorLikes(d.getDoctorId()),
						d.getAppointmentList().size(), adminService.getDoctorReports(d.getDoctorId()).size(),
						adminService.getDoctorWarnings(d.getDoctorId()))));

		// Sorting on the basis of likes
		Collections.sort(stats, Comparator.comparing(DoctorStatsDTO::getLikes).reversed());

		logger.info("Doctor Statistics Request by Admin Completed");
		return new ResponseEntity<>(stats, HttpStatus.OK);

	}

	@GetMapping("/appointmentStats")
	public ResponseEntity<List<StatsDTO>> getAppointmentStats() {

		List<Doctor> doctorStats = adminService.getAppointmentStats();
		List<StatsDTO> stats = new ArrayList<>();

		// Transferring required data to Map
		doctorStats.forEach(d -> stats.add(new StatsDTO(d.getDoctorSpecialization(), d.getAppointmentList().size())));

		logger.info("Appointment Statistics Request by Admin Completed");
		return new ResponseEntity<>(stats, HttpStatus.OK);

	}

	@GetMapping("/activity")
	public ResponseEntity<List<StatsDTO>> getActivityStats() {

		List<StatsDTO> stats = new ArrayList<>();
		List<UserActivity> activities = adminService.getUserActivity();

		stats.add(new StatsDTO("Morning", activities.stream()
				.filter(a -> a.getActivityTime().getHour() >= 05 && a.getActivityTime().getHour() < 12).count()));
		stats.add(new StatsDTO("Noon", activities.stream()
				.filter(a -> a.getActivityTime().getHour() >= 12 && a.getActivityTime().getHour() < 17).count()));
		stats.add(new StatsDTO("Evening", activities.stream()
				.filter(a -> a.getActivityTime().getHour() >= 17 && a.getActivityTime().getHour() < 22).count()));
		stats.add(new StatsDTO("Night", activities.stream()
				.filter(a -> a.getActivityTime().getHour() >= 22 || a.getActivityTime().getHour() < 05).count()));

		logger.info("Activity Statistics Request by Admin Completed");
		return new ResponseEntity<>(stats, HttpStatus.OK);
	}

	@GetMapping("/userStats")
	public ResponseEntity<List<StatsDTO>> getUserStats() {

		List<StatsDTO> stats = new ArrayList<>();

		stats.add(new StatsDTO("Blocked Doctors", adminService.getBlockedDoctors().size()));
		stats.add(new StatsDTO("Active Doctors", adminService.getActiveDoctors().size()));
		stats.add(new StatsDTO("Warned Doctors", adminService.getWarnedDoctors().size()));
		stats.add(new StatsDTO("Inactive Patients", adminService.getInactivePatients().size()));
		stats.add(new StatsDTO("Active Patients", adminService.getActivePatients().size()));

		logger.info("User Statistics Request by Admin Completed");
		return new ResponseEntity<>(stats, HttpStatus.OK);

	}

	@GetMapping("/feedbackStats")
	public ResponseEntity<List<StatsDTO>> getFeedbackStats() {

		List<StatsDTO> stats = new ArrayList<>();

		RestTemplate restTemp = new RestTemplate();
		DoctorRatingDTO doctorRating = restTemp.getForObject("http://localhost:9090/feedback/getfeedbacks",
				DoctorRatingDTO.class);

		List<FeedbackDTO> feedbackList = doctorRating.getListOfFeedback();

		stats.add(new StatsDTO("5-Star", feedbackList.stream().filter(f -> f.getFeedbackRating() == 5).count()));
		stats.add(new StatsDTO("4-Star", feedbackList.stream().filter(f -> f.getFeedbackRating() == 4).count()));
		stats.add(new StatsDTO("3-Star", feedbackList.stream().filter(f -> f.getFeedbackRating() == 3).count()));
		stats.add(new StatsDTO("2-Star", feedbackList.stream().filter(f -> f.getFeedbackRating() == 2).count()));
		stats.add(new StatsDTO("1-Star", feedbackList.stream().filter(f -> f.getFeedbackRating() == 1).count()));

		logger.info("Feedback Statistics Request by Admin Completed");
		return new ResponseEntity<>(stats, HttpStatus.OK);

	}
}