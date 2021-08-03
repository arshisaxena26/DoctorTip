package com.app.service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.model.Appointment;
import com.app.model.Prescription;
import com.app.repository.DoctorRepository;
import com.app.repository.PrescriptionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientServiceTest {

	@TestConfiguration
	static class PatientServiceContextConfiguration {
		@Bean
		public PatientService patientservice() {
			return new PatientService();
		}
	}

	@Autowired
	PatientService patientService;

	@MockBean
	DoctorRepository doctorRepository;

	@MockBean
	PrescriptionRepository prescriptionRepository;

	List<String> lisOfSp;
	Prescription prescription;

	@Before
	public void setUp() {
		// for fetching specialization
		lisOfSp = Stream.of("Ayurveda", "Dentist").collect(Collectors.toList());

		Mockito.when(doctorRepository.findDistinctSpecialization()).thenReturn(lisOfSp);

		// for show prescription
		Appointment appointment = new Appointment();
		appointment.setAppointmentId(1);
		prescription = new Prescription("Antibiotics", "Gargles", "Avoid Cold Drinks", Date.valueOf("2021-08-11"),
				appointment);

		Mockito.when(prescriptionRepository.checkPrescription(1)).thenReturn(prescription);
	}

	@Test
	public void getSpecializationTest() {
		List<String> found = patientService.getSpecialization();
		Assertions.assertArrayEquals(found.toArray(), lisOfSp.toArray());
	}

	@Test
	public void showPrescriptionTest() {
		Prescription found = patientService.showPrescription(1);
		Assertions.assertEquals(found.getPrescriptionContent(), prescription.getPrescriptionContent());
	}

}
