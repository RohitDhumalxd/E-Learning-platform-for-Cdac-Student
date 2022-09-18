package com.elearning.services;

import java.util.List;

import com.elearning.entities.Student;
import com.elearning.payloads.StudentDto;

public interface StudentService {
	
	StudentDto createStudent(StudentDto student);
	StudentDto updateStudent(StudentDto student,Integer studentId);
	StudentDto getStudentById(Integer studentId);
	List<StudentDto> getAllStudents();
	void deleteStudent(Integer Id);
	

}
