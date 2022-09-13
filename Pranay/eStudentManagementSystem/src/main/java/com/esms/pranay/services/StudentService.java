package com.esms.pranay.services;

import java.util.List;


import com.esms.pranay.payloads.StudentDto;

public interface StudentService {
	
	StudentDto registerNewStudent(StudentDto student);
	 StudentDto createStudent( StudentDto student);
	 StudentDto updateStudent( StudentDto student , Integer stdID);
	 StudentDto getStudnetByID( Integer stdID);
	 
	 // to get all users 
	 List<StudentDto> getAllStudents();
	 
	 // to delete user 
	 void deleteStudent(Integer stdID);

}
