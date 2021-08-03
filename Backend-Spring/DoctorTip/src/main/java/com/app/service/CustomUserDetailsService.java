package com.app.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dto.UserDTO;
import com.app.model.Doctor;
import com.app.model.GenericUser;
import com.app.model.Patient;
import com.app.model.UserActivity;
import com.app.repository.DoctorRepository;
import com.app.repository.PatientRepository;
import com.app.repository.UserActivityRepository;
import com.app.repository.UserRepository;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private UserActivityRepository userActivityRepository;

	@Autowired
	private UserRepository usrRepo;
	@Autowired
	private PatientRepository patientRepo;

	@Autowired
	private DoctorRepository doctorRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		GenericUser user = usrRepo.findByUserEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + email);
		}
		// return new
		// org.springframework.security.core.userdetails.User(user.getUserEmail(),
		// user.getUserPassword(),
		// new ArrayList<>());
		List<SimpleGrantedAuthority> roles = Arrays.asList(new SimpleGrantedAuthority(user.getUserRole()));
		return new User(user.getUserEmail(), user.getUserPassword(), roles);
	}

	public GenericUser save(UserDTO user) {
		GenericUser newUser = new GenericUser();
		newUser.setUserName(user.getUsername());
		newUser.setUserPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setUserRole(user.getUserRole());
		newUser.setUserEmail(user.getUserEmail());
		return usrRepo.save(newUser);
	}

	public GenericUser getUser(String email) {
		return usrRepo.findByUserEmail(email);
	}

	public void userActivity(GenericUser user, String validate) {
		UserActivity useractivity = new UserActivity();
		if (("login").equals(validate))
			useractivity.setActivityContent("LogIn");
		else
			useractivity.setActivityContent("LogOut");
		useractivity.setActivityTime(LocalDateTime.now());
		useractivity.setUser(user);
		userActivityRepository.save(useractivity);
	}

	// checking if profile is filled based on role
	public boolean foundProfileEntry(GenericUser user) {
		if ("ROLE_PATIENT".equals(user.getUserRole())) {
			Patient patientObj = patientRepo.getByUserId(user.getUserId());
			if (null == patientObj)
				return false;

		} else if ("ROLE_DOCTOR".equals(user.getUserRole())) {
			Doctor doctorObj = doctorRepo.getByUserId(user.getUserId());
			if (null == doctorObj)
				return false;
		}
		return true;
	}

}
