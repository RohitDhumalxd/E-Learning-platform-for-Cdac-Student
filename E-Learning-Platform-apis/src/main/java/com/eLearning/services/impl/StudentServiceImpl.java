package com.eLearning.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eLearning.exceptions.*;
import com.eLearning.entities.Student;
import com.eLearning.payloads.StudentDto;
import com.eLearning.repositories.StudentRepo;
import com.eLearning.services.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public StudentDto createStudent(StudentDto studentDto) {
		Student student=this.dtoToStudent(studentDto);
		Student savedStudent=this.studentRepo.save(student);
		return this.studentToDto(savedStudent);
	}

	@Override
	public StudentDto updateStudent(StudentDto studentDto, Integer studentID) {
		Student student=this.studentRepo.findById(studentID)
				.orElseThrow(()-> new ResourceNotFoundException("Student"," Id ",studentID));
		student.setName(studentDto.getName());
		student.setEmail(studentDto.getEmail());
		student.setPassword(studentDto.getPassword());
		student.setMobileNumber(studentDto.getMobileNumber());
		
		Student updateStudent = this.studentRepo.save(student);
		StudentDto studentDto1=this.studentToDto(updateStudent);
		return studentDto1;
	}

	@Override
	public StudentDto getStudentById(Integer studentID) {
		Student student=this.studentRepo.findById(studentID)
				.orElseThrow(()-> new ResourceNotFoundException("Student"," Id ",studentID));
		return this.studentToDto(student);
	}

	@Override
	public List<StudentDto> getAllStudents() {
		List<Student> s1=this.studentRepo.findAll();
		List<StudentDto> studentDto=s1.stream().map(student -> this.studentToDto(student)).collect(Collectors.toList());
		
		
		return studentDto;
	}

	@Override
	public void deleteStudent(Integer studentID) {
		// TODO Auto-generated method stub
		Student student=this.studentRepo.findById(studentID)
				.orElseThrow(()-> new ResourceNotFoundException("Student"," Id ",studentID));
		this.studentRepo.delete(student);
	}
	
	/*
	 * this Code will be avoided by Model Mapper library
	 * TO Connect Student and StudentDto 
	 * we separate them due to security reasons 
	 * */
	public Student dtoToStudent(StudentDto studentDto) {
//		Student s1=new Student();
		/*
		  s1.setId(studentDto.getId()); s1.setName(studentDto.getName());
		  s1.setEmail(studentDto.getEmail()); s1.setPassword(studentDto.getPassword());
		  s1.setMobileNumber(studentDto.getMobileNumber());
		  s1.setSecurity_question(studentDto.getSecurity_question());
		  s1.setSecurity_answer(studentDto.getSecurity_answer());
		 */
		Student s1=this.modelMapper.map(studentDto, Student.class);
		return s1;
	}
	
	public StudentDto studentToDto(Student s1) {
//		StudentDto dto=new StudentDto();
		/*
		  dto.setId(s1.getId()); dto.setName(s1.getName());
		  dto.setEmail(s1.getEmail()); dto.setPassword(s1.getPassword());
		  dto.setMobileNumber(s1.getMobileNumber());
		  dto.setSecurity_question(s1.getSecurity_question());
		  dto.setSecurity_answer(s1.getSecurity_answer());
		 */
		StudentDto dto=this.modelMapper.map(s1, StudentDto.class);
		return dto;
	}

}
