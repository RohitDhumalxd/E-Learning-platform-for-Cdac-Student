package com.eLearning.services;

import java.util.List;

import com.eLearning.payloads.StudentDto;

public interface StudentService {
	
	StudentDto createStudent(StudentDto student); 
	
	StudentDto updateStudent(StudentDto student,Integer studentID); 
	
	StudentDto getStudentById(Integer studentID); 
	
	List<StudentDto> getAllStudents();
	
	void deleteStudent(Integer studentID);
}
