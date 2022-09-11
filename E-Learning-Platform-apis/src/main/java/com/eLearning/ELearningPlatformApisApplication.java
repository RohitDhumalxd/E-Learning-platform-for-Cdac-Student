package com.eLearning;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.eLearning.config.AppConstants;
import com.eLearning.entities.Role;
import com.eLearning.repositories.RoleRepo;

@SpringBootApplication
public class ELearningPlatformApisApplication implements CommandLineRunner {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// for new user registration time to give Role ADMIN or NORMAL User
	@Autowired
	private RoleRepo repo;

	public static void main(String[] args) {
		SpringApplication.run(ELearningPlatformApisApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("Shivam@123"));	
		System.out.println(this.passwordEncoder.encode("Rohit@123"));	
		
		try {
			Role role=new Role();
			role.setId(AppConstants.ADMIN_USER);
			role.setName("ADMIN_USER");
			
			Role role1=new Role();
			role1.setId(AppConstants.NORMAL_USER);
			role1.setName("NORMAL_USER");
			
			// To save in data base
			List<Role> roles = List.of(role,role1);
			
			List<Role> result = this.repo.saveAll(roles);
			
			result.forEach(r ->{
				System.out.println(r.getName());
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
