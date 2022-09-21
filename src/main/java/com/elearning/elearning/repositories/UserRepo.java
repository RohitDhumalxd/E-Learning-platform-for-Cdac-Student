package com.elearning.elearning.repositories;

import java.util.Optional;

import com.elearning.elearning.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepo extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String username);

}
