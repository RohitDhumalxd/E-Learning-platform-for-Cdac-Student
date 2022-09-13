package com.esms.pranay.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.esms.pranay.entity.Student;
import com.esms.pranay.exception.ResourceNotFoundException;
import com.esms.pranay.repository.StudentRepo;

@Service
public class CustomStudentDetailService implements UserDetailsService {

	@Autowired
	StudentRepo studentRepo ;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		//loading student from BD by studnet Name 
		Student student = this.studentRepo.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("Student", "Email :" +username, 0));		
		
		return student;
	}

	
}
