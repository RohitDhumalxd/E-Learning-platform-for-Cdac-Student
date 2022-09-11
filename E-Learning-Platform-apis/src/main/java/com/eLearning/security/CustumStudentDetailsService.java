package com.eLearning.security;

import java.lang.module.ResolutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.eLearning.exceptions.NameNotFoundexception;
import com.eLearning.repositories.StudentRepo;

public class CustumStudentDetailsService  implements UserDetailsService{
	
	@Autowired
	private StudentRepo studentRepo;
	
	@Override
	public UserDetails loadUserByUsername(String studentname) throws UsernameNotFoundException {
		// Loading Student from database by studentName
		this.studentRepo.findByEmail(studentname).orElseThrow(()-> new NameNotFoundexception("Student"," email ",studentname));
		
		return null;
	}

}
