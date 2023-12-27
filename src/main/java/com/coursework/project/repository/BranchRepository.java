package com.coursework.project.repository;

import com.coursework.project.entity.Volunteer.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Long> {
}
