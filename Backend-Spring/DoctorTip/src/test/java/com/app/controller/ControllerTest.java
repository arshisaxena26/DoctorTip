package com.app.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
public class ControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private AdminController adminController;

	@Autowired
	private DoctorController doctorController;

	@Autowired
	private PatientController patientController;

	@Test
	public void adminControllerInitializedCorrectly() {
		assertThat(adminController).isNotNull();
	}

	@Test
	public void doctorControllerInitializedCorrectly() {
		assertThat(doctorController).isNotNull();
	}

	@Test
	public void patientControllerInitializedCorrectly() {
		assertThat(patientController).isNotNull();
	}

	@Test
	public void testGetPatients() throws Exception {
		mockMvc.perform(get("/admin/patients").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testGetDoctors() throws Exception {
		mockMvc.perform(get("/admin/doctors").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testGetUserNames() throws Exception {
		mockMvc.perform(get("/admin/users").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testGetStats() throws Exception {
		mockMvc.perform(get("/admin/stats").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testGetDoctorStats() throws Exception {
		mockMvc.perform(get("/admin/doctorStats").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testGetAppointmentStats() throws Exception {
		mockMvc.perform(get("/admin/appointmentStats").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testGetActivityStats() throws Exception {
		mockMvc.perform(get("/admin/activity").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testGetUserStats() throws Exception {
		mockMvc.perform(get("/admin/userStats").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testGetDoctorConcerns() throws Exception {
		mockMvc.perform(get("/admin/concerns/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testGetUserLogs() throws Exception {
		mockMvc.perform(get("/admin/logs/2").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testGetFeedbackStats() throws Exception {
		mockMvc.perform(get("/admin/feedbackStats").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testSetlike() throws Exception {
		mockMvc.perform(get("/doctor/like/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testSetlikeException() throws Exception {
		mockMvc.perform(get("/doctor/like/1000").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testGetDoctorsConcerns() throws Exception {
		mockMvc.perform(get("/doctor/concerns/rob@doctortip.com").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testGetDoctorsConcernsException() throws Exception {
		mockMvc.perform(get("/doctor/concerns/wrongEmail.com").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testGetSpecialization() throws Exception {

		mockMvc.perform(get("/patient/getspecializations").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0]", is("Dermatologist")));
	}

	@Test
	public void testFetchAvailableTimeSlot() throws Exception {
		mockMvc.perform(get("/patient/makeappointments").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("date", "2021-08-27").param("id", "1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.length()", is(4)));
	}

	@Test
	public void testShowPrescription() throws Exception {

		mockMvc.perform(get("/patient/prescriptions").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("appointmentId", "1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testShowPrescriptionForWrongId() throws Exception {

		mockMvc.perform(get("/patient/prescriptions").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("appointmentId", "1000").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	@Test
	public void testGetPatProfileForWrongId() throws Exception {

		mockMvc.perform(get("/patient/patprofile/1000").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testGetDocProfile() throws Exception {

		mockMvc.perform(get("/patient/docprofile/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testGetDocProfileForWrongId() throws Exception {
		mockMvc.perform(get("/patient/docprofile/1000").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
}
