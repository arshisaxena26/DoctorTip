package com.app.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.app.dto.AppointmentDTO;
import com.app.dto.ConcernResponseDTO;
import com.app.dto.DoctorDTO;
import com.app.model.Appointment;
import com.app.model.Comment;
import com.app.model.Concern;
import com.app.model.Doctor;
import com.app.model.DoctorAvailablity;
import com.app.model.GenericUser;
import com.app.model.Prescription;
import com.app.repository.AppointmentRepository;
import com.app.repository.CommentRepository;
import com.app.repository.DoctorAvailablityRepository;
import com.app.repository.DoctorRepository;
import com.app.repository.UserRepository;

@Service
@Transactional
public class DoctorService {
	@Autowired
	DoctorRepository doctorRepository;

	@Autowired
	AdminService adminService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	DoctorRepository doctorRepo;
	@Autowired
	AppointmentRepository appRepo;
	@Autowired
	DoctorAvailablityRepository doctorAvailRepo;
	@Autowired
	AppointmentRepository appointmentRepo;
	@Autowired
	CommentRepository commentRepo;
	@Autowired
	UserRepository userRepo;

	public Doctor getDoctor(String email) {

		GenericUser user = userRepo.findByUserEmail(email);
		return doctorRepo.getByUserId(user.getUserId());
	}

	public List<AppointmentDTO> getAppointment(String email) {

		GenericUser user = userRepo.findByUserEmail(email);
		Doctor doctor = doctorRepo.getByUserId(user.getUserId());
		List<AppointmentDTO> appointments = new ArrayList<>();
		doctor.getAppointmentList()
				.forEach(app -> appointments.add(new AppointmentDTO(app.getAppointmentDate().toString(),
						app.getAppointmentStatus(), app.getTimeSlot().getSlot(), app.getAppointmentId(),
						app.getPatient().getUser().getUserName(),
						app.getPrescription() == null ? 0 : app.getPrescription().getPrescriptionId())));

		return appointments;
	}

	public Appointment sendPrescription(Integer appointment_id, Prescription prescription) {
		Appointment appointment = appointmentRepo.findById(appointment_id).get();
		if (appointment.getPrescription() != null) {
			Prescription prep = appointment.getPrescription();
			prep.setDontS(prescription.getDontS());
			prep.setDoS(prescription.getDoS());
			prep.setFollowUp(prescription.getFollowUp());
			prep.setPrescriptionContent(prescription.getPrescriptionContent());
		} else {
			appointment.setPrescription(prescription);
			prescription.setAppointment(appointment);
		}
		return appointmentRepo.save(appointment);

	}

	public Appointment updateAppointmentStatus(@PathVariable int id) {
		Appointment appoint = appointmentRepo.getById(id);
		appoint.setAppointmentStatus("Attended");
		return appointmentRepo.save(appoint);
	}

//     public Doctor updateDoctorDate(Integer doctor_id, List<DoctorAvailablity> doctorAvail) {
	public void updateDoctorDate(Integer doctor_id, DoctorAvailablity doctorAvail) {

		Doctor doctor = doctorRepo.findById(doctor_id).get();
		doctorAvail.setDoctor(doctor);
		doctorAvailRepo.save(doctorAvail);
//		if (doctor.getAvailablity() != null && doctor.getAvailablity().size() > 0) {
//			List<DoctorAvailablity> list = doctor.getAvailablity();
//			doctorAvail.setDoctor(doctor);
//			list.add(doctorAvail);
//		} else {
//			List<DoctorAvailablity> list = new LinkedList<>();
//			list.add(doctorAvail);
//			doctor.setAvailablity(list);
//		}
////           doctor.setAvailablity(doctorAvail);
////           for(DoctorAvailablity doctorAvailablity : doctorAvail)
////           {
////                 doctorAvailablity.setDoctor(doctor);
////           }
////           doctorAvailRepo.saveAll(doctorAvail);
//		 doctorRepo.save(doctor);

	}

	public Comment setReply(Integer comment_id, String comment) {

		Comment commentCom = commentRepo.findById(comment_id).get();
		commentCom.setCommentReply(comment);
		return commentRepo.save(commentCom);
	}

	public Comment setLike(Integer comment_id) {
		System.out.println(comment_id);
		Comment commentCom = commentRepo.findById(comment_id).get();
		commentCom.setLiked(!commentCom.isLiked());
		Comment commentRespo = commentRepo.save(commentCom);
		System.out.println(commentRespo);
		return commentRespo;
//         if(commentCom.isLiked())
//               return "Liked";
//         else
//               return "UnLiked";
	}

	public Doctor saveDoctorProfile(DoctorDTO doctor) throws IOException {
		// creating doctor and store it in database
		Doctor doctorObj = new Doctor(doctor.getDoctorSpecialization(), doctor.getDoctorExperience(),
				doctor.getDoctorLocation(), doctor.getDoctorClinicName(), doctor.getDoctorConsultationFee(),
				doctor.getDoctorGender(), doctor.getDoctorPhone(), doctor.getDoctorImage().getBytes());

//	@Autowired
//	DoctorRepository doctorRepository;
//
//	@Autowired
//	UserRepository userRepository;
//
//	public Doctor saveDoctorProfile(DoctorDTO doctor) throws IOException {
//		// creating doctor object and store it in database
//		Doctor doctorObj = new Doctor(doctor.getDoctorSpecialization(), doctor.getDoctorExperience(),
//				doctor.getDoctorLocation(), doctor.getDoctorClinicName(), doctor.getDoctorConsultationFee(),
//				doctor.getDoctorGender(), doctor.getDoctorPhone(), doctor.getDoctorImage().getBytes());

		GenericUser user = userRepository.getById(doctor.getUserId());
		doctorObj.setUser(user);
		return doctorRepository.save(doctorObj);
	}

	public void deleteDoctorDate(Integer id) {
		DoctorAvailablity doctorAvailablity = doctorAvailRepo.getById(id);
		doctorAvailablity.setDoctor(null);
		doctorAvailRepo.delete(doctorAvailablity);
	}

	public List<ConcernResponseDTO> getDoctorConcern(String email) {
		GenericUser user = userRepo.findByUserEmail(email);
		Doctor doctor = doctorRepo.getByUserId(user.getUserId());
		List<Concern> concerns = adminService.getDoctorReports(doctor.getDoctorId());
		List<ConcernResponseDTO> concernDTO = new ArrayList<>();

		// Transferring required data to DTO
		concerns.forEach(c -> concernDTO.add(new ConcernResponseDTO(c.getConcernId(), c.getConcernComment(),
				c.getAppointment().getPatient().getUser().getUserName(), c.getAppointment().getAppointmentDate(),
				c.isWarned(), c.getAppointment().getDoctor().getUser().isBlocked())));

		return concernDTO;
	}

	public List<Comment> getDoctorComments(Integer doctorId) {
		return commentRepo.findByDoctor(doctorId);
	}
}
