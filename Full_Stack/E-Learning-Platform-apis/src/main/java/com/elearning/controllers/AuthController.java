package com.elearning.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearning.exceptions.ApiException;
import com.elearning.payloads.JwtAuthRequest;
import com.elearning.payloads.JwtAuthResponse;
import com.elearning.payloads.StudentDto;
import com.elearning.security.JwtTokenHelper;
import com.elearning.services.StudentService;

@RestController
@RequestMapping("/api/v1/auth/")

public class AuthController {
	
	@Autowired
	private JwtTokenHelper JwtTokenHelper;

	@Autowired
	private UserDetailsService userDetailService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(
			@RequestBody JwtAuthRequest request
			) throws Exception
	{
		this.authenticate(request.getUsername(), request.getPassword());
		
		UserDetails userDetails=this.userDetailService.loadUserByUsername(request.getUsername());
		
		String token = this.JwtTokenHelper.generateToken(userDetails);
		JwtAuthResponse response =new JwtAuthResponse();
		response.setToken(token);
		return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception {
		
		UsernamePasswordAuthenticationToken authenticationToken=
				new UsernamePasswordAuthenticationToken(username, password);
	    try {
			this.authenticationManager.authenticate(authenticationToken);
		} catch (BadCredentialsException e) {
			System.out.println("Invalid Details");
			throw new ApiException("Invalid username or password!!");
		}

	}
	
	//register new user api 

	@PostMapping("/register")
	public ResponseEntity<StudentDto> registerUser(@RequestBody StudentDto studentDto)
	{

		StudentDto registeredUser = this.studentService.registerNewUser(studentDto);
		return new ResponseEntity<StudentDto>(registeredUser,HttpStatus.CREATED);
		
	}
	

}
