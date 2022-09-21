package com.elearning.elearning.services.impl;

import com.elearning.elearning.entities.Branch;
import com.elearning.elearning.entities.User;
import com.elearning.elearning.exceptions.ResourceNotFoundException;
import com.elearning.elearning.payloads.BranchDto;
import com.elearning.elearning.payloads.UserDto;
import com.elearning.elearning.repositories.BranchRepo;
import com.elearning.elearning.repositories.UserRepo;
import com.elearning.elearning.services.BranchService;
import com.elearning.elearning.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchServiceImpl implements BranchService {

	@Autowired
	private BranchRepo branchRepo;
	@Autowired(required=true)
	private ModelMapper modelMapper;


	@Override
	public BranchDto createBranch(BranchDto branchDto) {
		Branch branch = this.modelMapper.map(branchDto, Branch.class);
		Branch savedBranch = this.branchRepo.save(branch);
		return this.modelMapper.map(savedBranch, BranchDto.class);

	}

	@Override
	public BranchDto updateUpdate(BranchDto branchDto, Integer branchId) {
		Branch branch = this.branchRepo.findById(branchId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id ", branchId));

		branch.setBranchName(branchDto.getTitle());
		branch.setDescription(branchDto.getDescription());
		Branch savedBranch = this.branchRepo.save(branch);
		return this.modelMapper.map(savedBranch, BranchDto.class);
	}

	@Override
	public List<BranchDto> getAllBranches() {
		List<Branch> branches = this.branchRepo.findAll();
		List<BranchDto> branchesDtos = branches.stream().map(branch -> (this.modelMapper.map(branch, BranchDto.class))).collect(Collectors.toList());
		return branchesDtos;
	}

	@Override
	public void deleteBranch(Integer branchId) {
		Branch branch = this.branchRepo.findById(branchId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id ", branchId));
		this.branchRepo.delete(branch);
	}
}
