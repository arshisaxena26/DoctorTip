package com.app.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Concern;
import com.app.model.Doctor;
import com.app.model.GenericUser;
import com.app.model.Patient;
import com.app.model.UserActivity;
import com.app.repository.AppointmentRepository;
import com.app.repository.ConcernRepository;
import com.app.repository.DoctorRepository;
import com.app.repository.LikesRepository;
import com.app.repository.PatientRepository;
import com.app.repository.UserActivityRepository;
import com.app.repository.UserRepository;

@Service
@Transactional
public class AdminService {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ConcernRepository concernRepository;

	@Autowired
	private UserActivityRepository activityRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private LikesRepository likesRepository;

	public List<Patient> getPatients() {
		return patientRepository.findAll();
	}

	public void deactivatePatient(Integer patientId) {
		// Getting User Object From Patient ID
		GenericUser user = getUser(getPatient(patientId).getUser().getUserId());
		user.setBlocked(true);
		userRepository.save(user);
	}

	public void activatePatient(Integer patientId) {
		// Getting User Object From Patient ID
		GenericUser user = getUser(getPatient(patientId).getUser().getUserId());
		user.setBlocked(false);
		userRepository.save(user);

		UserActivity activity = activityRepository.getUserActivity(user.getUserId());
		activity.setActivityTime(LocalDateTime.now());
		activityRepository.save(activity);
		activityRepository.save(new UserActivity("Unblocked", LocalDateTime.now(), user));
	}

	public List<Doctor> getDoctors() {
		return doctorRepository.findAll();
	}

	public List<Concern> getDoctorReports(Integer doctorId) {
		return concernRepository.findByDoctor(doctorId);
	}

	public void sendWarning(Integer concerId) {
		Concern concern = concernRepository.getById(concerId);
		concern.setWarned(true);
		concernRepository.save(concern);
	}

	public void unblockDoctor(Integer doctorId) {
		GenericUser user = getUser(getDoctor(doctorId).getUser().getUserId());
		user.setBlocked(false);
		userRepository.save(user);
		activityRepository.save(new UserActivity("Unblocked", LocalDateTime.now(), user));
	}

	public void blockDoctor(Integer doctorId) {
		GenericUser user = getUser(getDoctor(doctorId).getUser().getUserId());
		user.setBlocked(true);
		userRepository.save(user);
		activityRepository.save(new UserActivity("Blocked", LocalDateTime.now(), user));
	}

	public GenericUser getUser(Integer userId) {
		return userRepository.getById(userId);
	}

	public Doctor getDoctor(Integer doctorId) {
		return doctorRepository.getById(doctorId);
	}

	public long getPatientLastLoginMonth(int userId) {
		return LocalDateTime.from(activityRepository.getLoginDate(userId)).until(LocalDateTime.now(),
				ChronoUnit.MONTHS);
	}
	
	public LocalDateTime getPatientLastLogin(int userId) {
		return activityRepository.getLoginDate(userId);
	}

	public List<GenericUser> getUsers() {
		return userRepository.getUsers();
	}

	public List<UserActivity> getLogs(Integer userId) {
		return activityRepository.findByUser(getUser(userId));
	}

	public Integer getAppointments() {
		return appointmentRepository.findAll().size();
	}

	public Integer getUsersCount() {
		return getUsers().size();
	}

	public Integer getConcerns() {
		return concernRepository.findAll().size();
	}

	public Integer getReturningUsers() {
		return activityRepository.getReturningUserCount().size();
	}

	public Integer getDoctorWarnings(Integer doctorId) {
		return concernRepository.getWarnings(doctorId).size();
	}

	public Integer getDoctorLikes(Integer doctorId) {
		return likesRepository.getDoctorLikes(doctorId).size();
	}

	public List<Doctor> getAppointmentStats() {
		return doctorRepository.getAppointmentStats();
	}

	public List<Doctor> getBlockedDoctors() {
		return doctorRepository.getBlockedDoctors();
	}

	public List<Doctor> getActiveDoctors() {
		return doctorRepository.getActiveDoctors();
	}

	public List<Concern> getWarnedDoctors() {
		return concernRepository.getWarnedDoctors();
	}

	public List<Patient> getInactivePatients() {
		return patientRepository.getInactivePatients();
	}

	public List<Patient> getActivePatients() {
		return patientRepository.getActivePatients();
	}

	public List<UserActivity> getUserActivity() {
		return activityRepository.findAll();
	}

	public Patient getPatient(Integer patientId) {
		return patientRepository.getById(patientId);
	}

}
