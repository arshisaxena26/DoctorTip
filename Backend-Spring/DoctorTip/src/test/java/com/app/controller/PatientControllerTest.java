package com.app.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import com.app.dto.AppointmentDTO;
import com.app.dto.ConcernDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
public class PatientControllerTest {
	@Autowired
	private MockMvc mockMvc;

	private static ObjectMapper mapper = new ObjectMapper();

	@Test
	public void testGetSpecialization() throws Exception {

		mockMvc.perform(get("/patient/getspecializations").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0]", is("Dermatologist")));
	}

	@Test
	public void testGetDoctorListForDisplay() throws Exception {

		mockMvc.perform(post("/patient/listofdoctor").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("doctorSpecialization", "Dermatologist").param("appointmentDate", "2021-08-25")
				.param("doctorName", "").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name").value("Harry Styles"));
	}

	@Test
	public void testGetDoctorListForDisplayFOrWrongInput() throws Exception {

		mockMvc.perform(post("/patient/listofdoctor").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("doctorSpecialization", "").param("appointmentDate", "2021-06-27").param("doctorName", "")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(0));
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
				.param("appointmentId", "1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.message").value("aaa"));
	}

	@Test
	public void testShowPrescriptionForWrongId() throws Exception {

		mockMvc.perform(get("/patient/prescriptions").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("appointmentId", "2").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	@Test
	public void testGetPatProfile() throws Exception {

		mockMvc.perform(get("/patient/patprofile/1")

				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Emma Carstairs"));
	}

	@Test
	public void testGetPatProfileForWrongId() throws Exception {

		mockMvc.perform(get("/patient/patprofile/1000").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testGetDocProfile() throws Exception {

		mockMvc.perform(get("/patient/docprofile/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Harry Styles"));
	}

	@Test
	public void testGetDocProfileForWrongId() throws Exception {
		mockMvc.perform(get("/patient/docprofile/8").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testFillConcern() throws Exception {
		ConcernDTO concernDTO = new ConcernDTO("Test concern", 3);

		mockMvc.perform(post("/patient/concerns").content(mapper.writeValueAsString(concernDTO))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$").value(true));
	}

	@Test
	public void testFillConcernIfWrongInput() throws Exception {
		ConcernDTO concernDTO = new ConcernDTO("Test concern", 10);

		mockMvc.perform(post("/patient/concerns").content(mapper.writeValueAsString(concernDTO))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}

	@Test
	public void testSubmitAppointment() throws Exception {
		AppointmentDTO appointmentDTO = new AppointmentDTO(7, 2, "2021-08-28", 1);

		mockMvc.perform(post("/patient/submitappointment").content(mapper.writeValueAsString(appointmentDTO))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testSubmitAppointmentIfWrongInput() throws Exception {
		AppointmentDTO appointmentDTO = new AppointmentDTO(25, 55, "2021-08-28", 2);

		mockMvc.perform(post("/patient/submitappointment").content(mapper.writeValueAsString(appointmentDTO))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}
}
