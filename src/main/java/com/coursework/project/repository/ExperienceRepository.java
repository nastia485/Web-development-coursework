package com.coursework.project.repository;

import com.coursework.project.entity.Volunteer.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
}
