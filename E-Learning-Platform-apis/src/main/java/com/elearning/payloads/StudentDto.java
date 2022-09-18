package com.elearning.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class StudentDto {
	
	private int id;
	
	@NotEmpty
	@Size(min = 4,message = "Username must be minimum of 4 characters")
	private String name;
	
	@Email(message = "Email address is not vaild!!!")
	private String email;
	
	@NotEmpty
	@Size(min = 4, max = 12,message = "Password must be min of 4 char & max of 12 char!!")
	 @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
	    message = "Min 1 uppercase letter,"+ "Min 1 lowercase letter,"+ "Min 1 special character,"
	    				+ "Min 1 number,"+ "Min 8 characters")
	private String password;
	
	@NotNull
	private long phone;
	
	@NotEmpty
	private String security_question;
	@NotEmpty
	private String security_answer;
	

}
