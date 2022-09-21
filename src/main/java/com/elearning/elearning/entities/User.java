package com.elearning.elearning.entities;



import java.util.Date;
import java.util.stream.Collectors;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User
        //   implements UserDetails

{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "user_name", nullable = false, length = 100)
    private String name;

    @Column(name = "email", nullable = false, unique = true, length = 45)
    private String email;

    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @Column(name = "user_type", nullable = false, length = 20)
    private String userType;

   // @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "dob")
   // @Temporal(TemporalType.DATE)
    private String dob;

    @Column(name = "bio", nullable = true, length = 512)
    private String bio;


    @Column(name = "createdDateTime",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDateTime;



    @PrePersist
    public void onCreatedDateTime(){
        createdDateTime=new Date();
    }
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Post> posts = new ArrayList<>();

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role", referencedColumnName = "id"))
//    private Set<Role> roles = new HashSet<>();

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<SimpleGrantedAuthority> authorities = this.roles.stream()
//                .map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//        return authorities;
//    }

//    @Override
//    public String getUsername() {
//
//        return this.email;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//
//        return true;
//    }
}
