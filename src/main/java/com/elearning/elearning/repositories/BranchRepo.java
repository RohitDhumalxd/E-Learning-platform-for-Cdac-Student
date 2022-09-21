package com.elearning.elearning.repositories;

import com.elearning.elearning.entities.Branch;
import com.elearning.elearning.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BranchRepo extends JpaRepository<Branch, Integer> {

	//Optional<User> findByEmail(String username);

}
