package com.coursework.project.repository;

import com.coursework.project.entity.Volunteer.Concern;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcernRepository extends JpaRepository<Concern, Long> {
}
