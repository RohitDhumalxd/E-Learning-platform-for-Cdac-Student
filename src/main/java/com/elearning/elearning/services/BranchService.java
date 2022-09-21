package com.elearning.elearning.services;



import com.elearning.elearning.payloads.BranchDto;
import com.elearning.elearning.payloads.UserDto;

import java.util.List;


public interface BranchService {

	BranchDto createBranch(BranchDto branchDto);

	BranchDto updateUpdate(BranchDto branchDto, Integer userId);

	List<BranchDto> getAllBranches();

	void deleteBranch(Integer branchId);

}
