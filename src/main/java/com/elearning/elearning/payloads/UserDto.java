package com.elearning.elearning.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.*;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;
	@NotEmpty
	@Size(min = 4, message = "Username must have min 4 characters")
	private String name;

	@Email(message = "Your email is not valid")
	private String email;
	
	@NotEmpty
	@Size(min = 3,max=10,  message = "password must be of min 3 and max 10 characters")
	private String password;
	
	@NotEmpty
	private String userType;


	private String bio;

	@NotEmpty
	//@DateTimeFormat(pattern = "dd-MM-yyyy") //10-12-2002
	private String dob;
}