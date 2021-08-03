package com.app.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

	@Query(value = "select * from doctor where user_id= ?1", nativeQuery = true)
	public Doctor getByUserId(int userId);

	@Query("select d from Doctor d , DoctorAvailablity da where d.doctorId = da.doctor and :appointmentDate between da.availablityFrom and da.availablityTo")
	List<Doctor> searchDoctorByAvailablity(@Param("appointmentDate") LocalDate appointmentDate);

	@Query("Select d from Doctor d GROUP BY d.doctorSpecialization")
	List<Doctor> getAppointmentStats();

	@Query("Select d from Doctor d where d.user.isBlocked = true")
	List<Doctor> getBlockedDoctors();

	@Query("Select d from Doctor d where d.user.isBlocked = false")
	List<Doctor> getActiveDoctors();

	@Query("select distinct doctorSpecialization from Doctor")
	List<String> findDistinctSpecialization();

//	@Query("select d from Doctor d , DoctorAvailablity da where d.doctorId = da.doctor and :appointmentDate between da.availablityFrom and da.availablityTo and (d.doctorSpecialization=:specialization or d.user=:doctorObj)")
//	List<Doctor> searchDoctorBySpecializationOrName(@Param("appointmentDate") LocalDate appointmentDate,
//			@Param("specialization") String specialization, @Param("doctorObj") Optional<GenericUser> doctorObj);

	

	
}
