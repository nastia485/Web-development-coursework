package com.coursework.project.repository;

import com.coursework.project.entity.Volunteer.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QualificationRepository extends JpaRepository<Qualification, Long> {
}
