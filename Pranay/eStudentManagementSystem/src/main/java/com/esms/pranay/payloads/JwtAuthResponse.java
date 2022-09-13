package com.esms.pranay.payloads;

import lombok.Data;


@Data
public class JwtAuthResponse {


		private String token;
		
		private StudentDto student;
	

}
