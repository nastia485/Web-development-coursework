package com.coursework.project.repository;

import com.coursework.project.entity.Volunteer.Branch;
import com.coursework.project.entity.Volunteer.Concern;
import com.coursework.project.entity.Volunteer.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
    List<Volunteer> findByConcernAndIdIsNot(Concern concern, Long volunteerId);
}
