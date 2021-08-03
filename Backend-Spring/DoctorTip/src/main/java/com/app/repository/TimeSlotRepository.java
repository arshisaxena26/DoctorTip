package com.app.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.model.TimeSlot;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Integer> {

	@Query("select a from Appointment a where a.appointmentDate = :appointmentDate and a.doctor.doctorId= :doctorId")
	public List<TimeSlot> makeAppointment(@Param("appointmentDate") LocalDate date, @Param("doctorId") Integer id);
}
