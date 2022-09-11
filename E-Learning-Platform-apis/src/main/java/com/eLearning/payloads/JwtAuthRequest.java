package com.eLearning.payloads;

import lombok.Data;

@Data
public class JwtAuthRequest {
	//email== username
	private String username;
	private String password;
}
