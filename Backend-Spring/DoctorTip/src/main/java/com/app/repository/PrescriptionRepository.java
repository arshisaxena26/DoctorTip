package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.model.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Integer> {

	@Query("select p from Prescription p where p.appointment.appointmentId = :appointmentId")
	public Prescription checkPrescription(@Param("appointmentId") Integer appointmentId);
}
