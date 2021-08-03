package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.DoctorAvailablity;

public interface DoctorAvailablityRepository extends JpaRepository<DoctorAvailablity, Integer> {

}
