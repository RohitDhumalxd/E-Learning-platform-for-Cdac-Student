package com.elearning.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.entities.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {
	
	
	Optional<Student> findByEmail(String email);
}
