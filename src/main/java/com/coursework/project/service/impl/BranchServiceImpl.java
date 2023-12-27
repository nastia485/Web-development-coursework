package com.coursework.project.service.impl;

import com.coursework.project.entity.Volunteer.Branch;
import com.coursework.project.repository.BranchRepository;
import com.coursework.project.service.BranchService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchServiceImpl implements BranchService {
    private BranchRepository branchRepository;

    public BranchServiceImpl(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }
}
