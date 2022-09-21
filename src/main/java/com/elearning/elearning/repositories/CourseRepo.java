package com.elearning.elearning.repositories;

import com.elearning.elearning.entities.Course;
import com.elearning.elearning.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CourseRepo extends JpaRepository<Course, Integer> {

	//Optional<User> findByEmail(String username);

}
