package com.eLearning.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "students")
@NoArgsConstructor
@Getter
@Setter
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "student_name", nullable = false, length = 100)
	private String name;

	private String email;

	private String password;

	private long mobileNumber;

	private String security_question;

	private String security_answer;

	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name = "student_role", joinColumns = @JoinColumn(name = "student", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "role", referencedColumnName = "id"))
	private Set<Role> roles = new HashSet<>();

}
