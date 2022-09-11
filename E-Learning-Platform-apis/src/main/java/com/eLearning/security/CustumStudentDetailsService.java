package com.eLearning.security;

import java.lang.module.ResolutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eLearning.entities.Student;
import com.eLearning.exceptions.NameNotFoundexception;
import com.eLearning.repositories.StudentRepo;
@Service
public class CustumStudentDetailsService  implements UserDetailsService{
	
	@Autowired
	private StudentRepo studentRepo;
	
	@Override
	public UserDetails loadUserByUsername(String studentname) throws UsernameNotFoundException {
		// Loading Student from database by studentName
		Student student=this.studentRepo.findByEmail(studentname).orElseThrow(()-> new NameNotFoundexception("Student"," email ",studentname));
		
		return student;
	}

}
