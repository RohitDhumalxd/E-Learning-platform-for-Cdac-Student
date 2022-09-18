package com.elearning.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="students")
@NoArgsConstructor
@Getter
@Setter
public class Student {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	
	@Column(name = "student_name", nullable = false, length = 100)
	private String name;
	private String email;
	private String password;
	private long phone;
	private String security_question;
	private String security_answer;

}
