package com.esms.pranay.services.impl;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.esms.pranay.exception.*;
import com.esms.pranay.payloads.StudentDto;
import com.esms.pranay.confi.AppConstants;
import com.esms.pranay.entity.Role;
import com.esms.pranay.entity.Student;

import com.esms.pranay.repository.RoleRepo;
import com.esms.pranay.repository.StudentRepo;
import com.esms.pranay.services.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepo studentRepo;
	
	@Autowired
	ModelMapper modelMapper ; 
	
	@Autowired
	private PasswordEncoder passwordEncoder ;
	
	@Autowired
	private RoleRepo roleRepo ;

	@Override
	public StudentDto createStudent(StudentDto studentDto) {

		Student student = this.dtoToStudent(studentDto);
		Student savedStudent = this.studentRepo.save(student);
		return this.studentTOStudnetDto(savedStudent);
	}

	@Override
	public StudentDto updateStudent(StudentDto studentDto, Integer stdID) {

		Student student = this.studentRepo.findById(stdID)
				.orElseThrow(() -> new ResourceNotFoundException("Student", "id", stdID));

		student.setPincode(studentDto.getPincode());
		student.setPhone(studentDto.getPhone());
		student.setPassword(studentDto.getPassword());
		student.setLname(studentDto.getLname());
		student.setFName(studentDto.getFName());
		student.setEmail(studentDto.getEmail());
		student.setCity(studentDto.getCity());
		student.setBirthday(studentDto.getBirthday());
		student.setAddress(studentDto.getAddress());

		Student updatedStudent = this.studentRepo.save(student);

		StudentDto studentDto1 = this.studentTOStudnetDto(updatedStudent);

		return studentDto1;

	}

	@Override
	public StudentDto getStudnetByID(Integer stdID) {

		Student student = this.studentRepo.findById(stdID)
				.orElseThrow(() -> new ResourceNotFoundException("Student", "id", stdID));
		return this.studentTOStudnetDto(student);
	}

	@Override
	public List<StudentDto> getAllStudents() {

		List<Student> student = this.studentRepo.findAll();
		List<StudentDto> studentDto = student.stream().map(students -> this.studentTOStudnetDto(students))
				.collect(Collectors.toList());
		return studentDto;
	}

	@Override
	public void deleteStudent(Integer stdID) {

		Student student = this.studentRepo.findById(stdID)
				.orElseThrow(() -> new ResourceNotFoundException("Student ", "stdID", stdID));
		this.studentRepo.delete(student);
	}

	// changing StudentDto to Studdet

	private Student dtoToStudent(StudentDto studentDto) {
		
		Student student = this.modelMapper.map(studentDto, Student.class);
		
		return student ;
		
//		Student std = new Student();
		
//		std.setStdId(studentDto.getStdId());
//		std.setPincode(studentDto.getPincode());
//		std.setPhone(studentDto.getPhone());
//		std.setPassword(studentDto.getPassword());
//		std.setLname(studentDto.getLname());
//		std.setFName(studentDto.getFName());
//		std.setEmail(studentDto.getEmail());
//		std.setCity(studentDto.getCity());
//		std.setBirthday(studentDto.getBirthday());
//		std.setAddress(studentDto.getAddress());

	}

	private StudentDto studentTOStudnetDto(Student student) {

		StudentDto stddto = this.modelMapper.map(student , StudentDto.class);
		return stddto;
		
//		StudentDto stddto = new StudentDto();		

//		stddto.setStdId(student.getStdId());
//		stddto.setPincode(student.getPincode());
//		stddto.setPhone(student.getPhone());
//		stddto.setPassword(student.getPassword());
//		stddto.setLname(student.getLname());
//		stddto.setFName(student.getFName());
//		stddto.setEmail(student.getEmail());
//		stddto.setCity(student.getCity());
//		stddto.setBirthday(student.getBirthday());
//		stddto.setAddress(student.getAddress());

	}

	@Override
	public StudentDto registerNewStudent(StudentDto studentDto) {
		
		Student student = this.modelMapper.map(studentDto, Student.class);
		
		//encopded the password 
		student.setPassword(this.passwordEncoder.encode(student.getPassword()));

		//roles 
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		student.getRoles().add(role);
		Student newStudent = this.studentRepo.save(student);
		
		return this.modelMapper.map(newStudent, StudentDto.class);
		
	
	}

}
