package com.elearning.elearning.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class CourseDto {

	private int id;
	@NotEmpty
	@Size(min = 4, message = "Title must have min 4 characters")
	private String title;
	private String description;
	

}