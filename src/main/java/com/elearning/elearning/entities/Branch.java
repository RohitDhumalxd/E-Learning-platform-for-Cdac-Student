package com.elearning.elearning.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "branches")
@NoArgsConstructor
@Getter
@Setter
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "branch_name", nullable = false, length = 100)
    private String branchName;

    @Column(name = "description", nullable = true, length = 100)
    private String description;

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="id")
    private List<Course> courses;
}
