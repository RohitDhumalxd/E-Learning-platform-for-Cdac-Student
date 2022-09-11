package com.eLearning.payloads;



import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * To Transfer data to services
 * Dto--- Data transfer Object
 * */

@NoArgsConstructor
@Getter
@Setter
public class StudentDto {

	private int id;
	
	@NotEmpty
	private String name;
	
	@Email(message = "Email address is not vaild!!!!!!!")
	private String email;
	
	@NotEmpty
	//@Size(min = 4, max = 12,message = "Password must be min of 4 char & max of 12 char")
	
	 @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
    message = "Min 1 uppercase letter,"+ "Min 1 lowercase letter,"+ "Min 1 special character,"
    				+ "Min 1 number,"+ "Min 8 characters")
	private String password;
	
	@NotNull
	private long mobileNumber;
	
	@NotEmpty
	private String security_question;
	
	@NotEmpty
	private String security_answer;
	
	private Set<RoleDto> roles =new HashSet<>();
}
