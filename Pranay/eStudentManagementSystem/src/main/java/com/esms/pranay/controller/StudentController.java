package com.esms.pranay.controller;



import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.esms.pranay.payloads.StudentDto;
import com.esms.pranay.services.StudentService;

@RestController
@RequestMapping("/api/std")
public class StudentController {

	@Autowired
	private StudentService studentService ; 
	// POST - create Student 
	
	@PostMapping("/")
	public ResponseEntity<StudentDto> createStudent(@Valid @RequestBody StudentDto studentDto){
		
		StudentDto createStudentDto = this.studentService.createStudent(studentDto);
		return new ResponseEntity<>(createStudentDto, HttpStatus.CREATED);
	}
	
	
	
	//Put /- for update Student
	
	@PutMapping("/{stdID}")
	public ResponseEntity<StudentDto> updateStudent(@Valid @RequestBody StudentDto studentdto, @PathVariable Integer stdID){
		
		StudentDto updateStudent = this.studentService.updateStudent(studentdto, stdID);
		return  ResponseEntity.ok(updateStudent);	
	}
	
	
	//Delete Student 
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{stdID}")
	public ResponseEntity<?> deleteStudent(@PathVariable  Integer stdID){
		
		this.studentService.deleteStudent(stdID);
		return new ResponseEntity(Map.of("message" , " Student Deleted Succuessfully !.."),HttpStatus.OK);
	}
	
	
	
	//GET get student 
	@GetMapping("/")
	public ResponseEntity<List<StudentDto>> getAllStudents(){
		
		return ResponseEntity.ok(this.studentService.getAllStudents());
	}
	
	//GET get single student 
	@GetMapping("/{stdID}")
	public ResponseEntity<StudentDto> getSingleStudents(@PathVariable  Integer stdID){
			
		return ResponseEntity.ok(this.studentService.getStudnetByID(stdID));
		}
}





