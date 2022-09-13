package com.esms.pranay.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "students_details")
@NoArgsConstructor
@Getter
@Setter
public class Student implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int stdId;

//	@Column(nullable = false)
	private String fName;
//	@Column(nullable = false)
	private String lname;

//	@Column(nullable = false , length = 30) 
	private String email;

	// @Column(nullable = false)
	private String password;
	private String birthday;
	private int phone;

	// @Column(nullable = false)
	private int pincode;
	private String city;
	private String address;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "Student_Role", joinColumns = @JoinColumn(name = "students_details", referencedColumnName = "stdId"), inverseJoinColumns = @JoinColumn(name = "Role", referencedColumnName = "id"))

	private Set<Role> roles = new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
	List<SimpleGrantedAuthority> authorities =	this.roles.stream().map((role)-> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
		return authorities; 
		
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
