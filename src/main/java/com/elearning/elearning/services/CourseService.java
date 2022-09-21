package com.elearning.elearning.services;



import com.elearning.elearning.payloads.BranchDto;
import com.elearning.elearning.payloads.CourseDto;

import java.util.List;


public interface CourseService {

	CourseDto createBranch(CourseDto courseDto);

	CourseDto updateUpdate(CourseDto courseDto, Integer userId);

	List<BranchDto> getAllBranches();

	void deleteBranch(Integer courseId);

}
