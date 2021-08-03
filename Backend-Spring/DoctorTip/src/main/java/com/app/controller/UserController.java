package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.EmailDTO;
import com.app.dto.JwtRequest;
import com.app.dto.JwtResponse;
import com.app.dto.UserDTO;
import com.app.jwtconfig.JwtUtil;
import com.app.model.GenericUser;
import com.app.service.CustomUserDetailsService;

@CrossOrigin
@RestController
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
			throws Exception {

		authenticate(authenticationRequest.getUserEmail(), authenticationRequest.getUserPassword());
		GenericUser user = userDetailsService.getUser(authenticationRequest.getUserEmail());
		if (user.isBlocked()) {
			return new ResponseEntity<>(HttpStatus.LOCKED);
		} else {
			final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserEmail());

			final String token = jwtTokenUtil.generateToken(userDetails);

			userDetailsService.userActivity(user, "login");
			boolean foundEntry = userDetailsService.foundProfileEntry(user);
			return ResponseEntity
					.ok(new JwtResponse(token, user.getUserId(), user.getUserName(), user.getUserRole(), foundEntry));
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<GenericUser> saveUser(@RequestBody UserDTO user) throws Exception {
		return ResponseEntity.ok(userDetailsService.save(user));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	@RequestMapping(value = "/checkEmail", method = RequestMethod.POST)
	public boolean emailValidation(@RequestBody EmailDTO email) throws Exception {

		GenericUser user = userDetailsService.getUser(email.getEmail());
		if (user != null)
			return true;
		return false;
	}

	@RequestMapping(value = "/logoutt", method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> logout(@RequestBody EmailDTO email) throws Exception {
		GenericUser user = userDetailsService.getUser(email.getEmail());
		userDetailsService.userActivity(user, "logout");
		return ResponseEntity.ok(HttpStatus.OK);
	}

}
