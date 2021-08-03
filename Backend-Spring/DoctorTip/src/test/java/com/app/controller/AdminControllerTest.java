package com.app.controller;

import static org.assertj.core.api.Assertions.assertThat;
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

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
public class AdminControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private AdminController controller;

	private static ObjectMapper mapper = new ObjectMapper();

	@Test
	public void controllerInitializedCorrectly() {
		assertThat(controller).isNotNull();
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
	public void testDeactivatePatient() throws Exception {
		String json = mapper.writeValueAsString(2);
		mockMvc.perform(put("/admin/deactivate").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testDeactivatePatientException() throws Exception {
		String json = mapper.writeValueAsString(100);
		mockMvc.perform(put("/admin/deactivate").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	@Test
	public void testActivatePatient() throws Exception {
		String json = mapper.writeValueAsString(2);
		mockMvc.perform(put("/admin/activate").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testActivatePatientException() throws Exception {
		String json = mapper.writeValueAsString(100);
		mockMvc.perform(put("/admin/activate").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	@Test
	public void testBlockDoctor() throws Exception {
		String json = mapper.writeValueAsString(2);
		mockMvc.perform(put("/admin/block").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testBlockDoctorException() throws Exception {
		String json = mapper.writeValueAsString(100);
		mockMvc.perform(put("/admin/block").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	@Test
	public void testUnblockDoctor() throws Exception {
		String json = mapper.writeValueAsString(2);
		mockMvc.perform(put("/admin/unblock").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testUnblockDoctorException() throws Exception {
		String json = mapper.writeValueAsString(100);
		mockMvc.perform(put("/admin/unblock").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	@Test
	public void testSendWarning() throws Exception {
		String json = mapper.writeValueAsString(2);
		mockMvc.perform(put("/admin/warning").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testSendWarningException() throws Exception {
		String json = mapper.writeValueAsString(100);
		mockMvc.perform(put("/admin/warning").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}

}
