package com.eLearning.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eLearning.exceptions.ApiException;
import com.eLearning.payloads.JwtAuthRequest;
import com.eLearning.payloads.JwtAuthResponse;
import com.eLearning.payloads.StudentDto;
import com.eLearning.security.JwtTokenHelper;
import com.eLearning.services.StudentService;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception
	{
		this.authenticate(request.getUsername(),request.getPassword());
		
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		String token = this.jwtTokenHelper.generateToken(userDetails);
		JwtAuthResponse response =new JwtAuthResponse();
		response.setToken(token);
		return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
	}

	// Used for Authenticate UserName And password
	private void authenticate(String username, String password) throws Exception {
	UsernamePasswordAuthenticationToken authenticationToken=
					new UsernamePasswordAuthenticationToken(username, password);
		
			try {
				this.authenticationManager.authenticate(authenticationToken);
			} catch (BadCredentialsException e) {
				System.out.println("Invalid Details");
				throw new ApiException("Invalid username or password !!");
			}

	}
	
	// Register New User/Student Api
	@PostMapping("/register")
	public ResponseEntity<StudentDto> registerSudent(@RequestBody StudentDto studentDto){
		
		StudentDto registeredUser = this.studentService.registerNewUser(studentDto);
		return new ResponseEntity<StudentDto>(registeredUser,HttpStatus.CREATED);
	}
	
	
	
	
	
	
}
