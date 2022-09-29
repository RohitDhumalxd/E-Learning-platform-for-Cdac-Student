package com.elearning.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.elearning.exceptions.*;
import com.elearning.config.AppConstants;
import com.elearning.dao.RoleRepo;
import com.elearning.dao.StudentRepo;
import com.elearning.entities.Role;
import com.elearning.entities.Student;
import com.elearning.payloads.StudentDto;
import com.elearning.services.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;

	@Override
	public StudentDto createStudent(StudentDto studentDto) {
		
		Student student = this.dtoToStudent(studentDto);
		
		Student savedStudent=this.studentRepo.save(student);
		
		return this.studentToDto(savedStudent);
	}

	@Override
	public StudentDto updateStudent(StudentDto studentDto, Integer studentId) {
		
		Student student = this.studentRepo.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("Student"," id ",studentId));
				
		student.setName(studentDto.getName());
		student.setEmail(studentDto.getEmail());
		student.setPassword(studentDto.getPassword());
		student.setPhone(studentDto.getPhone());
		
		Student updateStudent = this.studentRepo.save(student);
		StudentDto studentDto1=this.studentToDto(updateStudent);
		return studentDto1;
		
	
	}

	@Override
	public StudentDto getStudentById(Integer studentId) {
		
		Student student=this.studentRepo.findById(studentId)
				.orElseThrow(()-> new ResourceNotFoundException("Student"," Id ",studentId));
		return this.studentToDto(student);
	}

	@Override
	public List<StudentDto> getAllStudents() {
		List<Student> students=this.studentRepo.findAll();
		List<StudentDto> studentDtos=students.stream().map(student -> this.studentToDto(student)).collect(Collectors.toList());
		
		
		return studentDtos;
	}

	@Override
	public void deleteStudent(Integer studentId) {
		Student student=this.studentRepo.findById(studentId)
				.orElseThrow(()-> new ResourceNotFoundException("Student"," Id ",studentId));
		this.studentRepo.delete(student);

	}
	
	public Student dtoToStudent(StudentDto studentDto) {

/*		  Student student = new Student();
		  student.setId(studentDto.getId());
		  student.setName(studentDto.getName());
		  student.setEmail(studentDto.getEmail());
		  student.setPassword(studentDto.getPassword());
		  student.setPhone(studentDto.getPhone());
		  student.setSecurity_question(studentDto.getSecurity_question());
		  student.setSecurity_answer(studentDto.getSecurity_answer());
		  return student;
		*/
		Student student=this.modelMapper.map(studentDto, Student.class);
		return student;
	}
	
	public StudentDto studentToDto(Student  student) {
/*		StudentDto studentDto=new StudentDto();
	
		studentDto.setId(student.getId());
		studentDto.setName(student.getName());
		studentDto.setEmail(student.getEmail());
		studentDto.setPassword(student.getPassword());
		studentDto.setPhone(student.getPhone());
		studentDto.setSecurity_question(student.getSecurity_question());
		studentDto.setSecurity_answer(student.getSecurity_answer());
		  return studentDto;
		*/
		StudentDto studentDto = this.modelMapper.map(student, StudentDto.class);
		return studentDto;
		
	}

	@Override
	public StudentDto registerNewUser(StudentDto userDto) {
		
		Student student = this.modelMapper.map(userDto, Student.class);
		
		
		//encoded the password
		student.setPassword(this.passwordEncoder.encode(student.getPassword()));
		
		//roles
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		
		student.getRoles().add(role);
		

		Student newStudentUser = this.studentRepo.save(student);
		
		return this.modelMapper.map(newStudentUser, StudentDto.class);
		
		
	
	}

}
