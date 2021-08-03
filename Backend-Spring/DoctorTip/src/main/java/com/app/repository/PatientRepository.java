package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

	@Query(value = "select * from patient where user_id= ?1", nativeQuery = true)
	public Patient getByUserId(int userId);

	@Query("select p from Patient p where p.user.isBlocked = true")
	public List<Patient> getInactivePatients();

	@Query("select p from Patient p where p.user.isBlocked = false")
	public List<Patient> getActivePatients();
	
	@Query("select p from Patient p where p.user.userId=:userId")
	Patient findByUserId(int userId);

}
