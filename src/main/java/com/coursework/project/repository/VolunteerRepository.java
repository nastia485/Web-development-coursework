package com.coursework.project.repository;

import com.coursework.project.entity.Volunteer.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
}
