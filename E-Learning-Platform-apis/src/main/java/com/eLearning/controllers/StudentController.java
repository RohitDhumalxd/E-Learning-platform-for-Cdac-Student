package com.eLearning.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eLearning.payloads.*;
import com.eLearning.services.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
		
	//  POST - create Student/User
	
	@PostMapping("/")
	public ResponseEntity<StudentDto> createStudent(@Valid @RequestBody StudentDto studentDto)
	{
		StudentDto createStudentDto=this.studentService.createStudent(studentDto);
		return new ResponseEntity<>(createStudentDto, HttpStatus.CREATED);
	}
	
	// PUT - update Student / user
	
	@PutMapping("/{studentID}")//{studentId} -- Path url variable
	public ResponseEntity<StudentDto> UpdateStudent( @Valid @RequestBody StudentDto studentDto,@PathVariable ("studentID") Integer sId){
		StudentDto updateStudent = this.studentService.updateStudent(studentDto, sId);
		return ResponseEntity.ok(updateStudent);
		
	}
	
	
	// DELETE - delete Student / user
	@DeleteMapping("/{studentID}")
	public ResponseEntity<ApiResponse> deleteStudent(@PathVariable ("studentID") Integer sId){
		this.studentService.deleteStudent(sId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Student Deleted Successfully",true),HttpStatus.OK);
	}
	
	// GET - user getAll Students 
	
	@GetMapping("/")
	public ResponseEntity<List<StudentDto>> getAllStudents()
	{
		return ResponseEntity.ok(this.studentService.getAllStudents());
	}
	// GET - user get single Student
	@GetMapping("/{studentID}")
	public ResponseEntity<StudentDto> getSingleStudent(@PathVariable Integer studentID)
	{
		return ResponseEntity.ok(this.studentService.getStudentById(studentID));
	}
}
