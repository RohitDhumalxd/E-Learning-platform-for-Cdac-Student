package com.esms.pranay.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esms.pranay.entity.Student;

public interface  StudentRepo extends JpaRepository<Student, Integer>{

	
	Optional<Student> findByEmail(String Email);
}
