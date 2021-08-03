package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.model.Concern;

public interface ConcernRepository extends JpaRepository<Concern, Integer> {

	@Query("select c from Concern c where c.appointment.doctor.doctorId = :doctor_id")
	public List<Concern> findByDoctor(@Param("doctor_id") Integer doctorId);

	@Query("select c from Concern c where c.appointment.doctor.doctorId = :doctor_id and c.isWarned=true")
	public List<Concern> getWarnings(@Param("doctor_id") Integer doctorId);

	@Query("select c from Concern c where c.isWarned=true group by c.appointment.doctor")
	public List<Concern> getWarnedDoctors();

}
