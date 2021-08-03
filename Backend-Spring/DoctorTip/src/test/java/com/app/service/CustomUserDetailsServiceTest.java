package com.app.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.model.GenericUser;
import com.app.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomUserDetailsServiceTest {

	@TestConfiguration
	static class CustomUserDetailsServiceTestContextConfiguration {
		@Bean
		public CustomUserDetailsService customUserDetails() {
			return new CustomUserDetailsService();
		}
	}

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@MockBean
	private UserRepository usrRepo;

	GenericUser user;

	@Before
	public void setUp() {
		user = new GenericUser("alex", "abc@gmail.com", "Abcdef@123", "Doctor");
		Mockito.when(usrRepo.findByUserEmail("abc@gmail.com")).thenReturn(user);
	}

	@Test
	public void whenValidName_thenEmployeeShouldBeFound() {
		String email = "abc@gmail.com";
		UserDetails found = customUserDetailsService.loadUserByUsername(email);

		assertThat(found.getUsername()).isEqualTo(user.getUserEmail());
	}
}
