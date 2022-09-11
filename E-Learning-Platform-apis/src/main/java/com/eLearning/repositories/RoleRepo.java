package com.eLearning.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eLearning.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
