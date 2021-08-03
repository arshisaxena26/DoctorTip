package com.app.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
	@Query("select a from Appointment a where a.appointmentDate = :appointmentDate and a.doctor.doctorId= :doctorId")
	public List<Appointment> fetchBookedTimeslot(@Param("appointmentDate") LocalDate date,
			@Param("doctorId") Integer id);

	@Query("select a from Appointment a where a.patient.user.userId= :patientId")
	public List<Appointment> listOfAppointment(@Param("patientId") Integer patientId);
}
