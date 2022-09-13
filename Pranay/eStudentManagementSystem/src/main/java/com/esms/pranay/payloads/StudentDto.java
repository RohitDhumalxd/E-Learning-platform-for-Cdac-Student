package com.esms.pranay.payloads;



import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.esms.pranay.entity.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class StudentDto {

	private int stdId ;
	
	@NotEmpty
	@Size(min=4, message = "User Name is invalid it must be min of 4 Characters @!")
	private String  fName ; 
	@Size(min=4, message = "User Name is invalid it must be min of 4 Characters @!")
	private String  lname ;
	
	@Email(message="Email address is not valid !")
	private String  email ; 
	
	@NotNull
	@Size(min=3, max=10 , message="must be min of 3 and max of 10 characters !")
//	@Pattern(regexp = “^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8, 20}$”)
	private String  password ;
	private String  birthday ;
	private int  phone ;
	private int pincode ;
	private String city ; 
	private String address ;
	private Set<RoleDto> roles = new HashSet<>();
	
}
