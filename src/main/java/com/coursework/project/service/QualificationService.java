package com.coursework.project.service;

import com.coursework.project.entity.Volunteer.Branch;
import com.coursework.project.entity.Volunteer.Qualification;
import com.coursework.project.repository.QualificationRepository;

import java.util.List;

public interface QualificationService {
    List<Qualification> getAllQualifications();

}
