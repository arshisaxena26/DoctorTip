package com.app.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
public class DoctorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private DoctorController controller;

	// private static ObjectMapper mapper = new ObjectMapper();

	@Test
	public void controllerInitializedCorrectly() {
		assertThat(controller).isNotNull();
	}

	@Test
	public void testUpdateAppointment() throws Exception {
		mockMvc.perform(put("/doctor/updateAppointmentStatus/1").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testUpdateAppointmentException() throws Exception {
		mockMvc.perform(put("/doctor/updateAppointmentStatus/1000").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
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
	public void testdeleteDoctor() throws Exception {
		mockMvc.perform(delete("/doctor/deleteAvailability/3").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testdeleteDoctorException() throws Exception {
		mockMvc.perform(delete("/doctor/deleteAvailability/1000").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

}
