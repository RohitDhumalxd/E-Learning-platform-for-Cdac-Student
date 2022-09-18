package com.elearning.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearning.payloads.ApiResponse;
import com.elearning.payloads.StudentDto;
import com.elearning.services.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	//POST - create user
	@PostMapping("/")
	public ResponseEntity<StudentDto> createStudent(@Valid @RequestBody StudentDto studentDto)
	{
		StudentDto createStudentDto=this.studentService.createStudent(studentDto);
		return new ResponseEntity<>(createStudentDto, HttpStatus.CREATED);
	}
	
	
	//PUT - update user
	

	@PutMapping("/{studentId}")//{studentId} -- Path url variable
	public ResponseEntity<StudentDto> UpdateStudent(@Valid @RequestBody StudentDto studentDto,@PathVariable ("studentId") Integer sId){
		StudentDto updateStudent = this.studentService.updateStudent(studentDto, sId);
		return ResponseEntity.ok(updateStudent);
		
	}
	//DELETE - delete  user
	
	@DeleteMapping("/{studentId}")
	public ResponseEntity<ApiResponse> deleteStudent(@PathVariable ("studentId") Integer sId){
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
		@GetMapping("/{studentId}")
		public ResponseEntity<StudentDto> getSingleStudent(@PathVariable Integer studentId)
		{
			return ResponseEntity.ok(this.studentService.getStudentById(studentId));
		}

}
