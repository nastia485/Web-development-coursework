package com.coursework.project.service.impl;

import com.coursework.project.entity.Volunteer.Experience;
import com.coursework.project.entity.Volunteer.Qualification;
import com.coursework.project.repository.ExperienceRepository;
import com.coursework.project.service.ExperienceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceServiceImpl implements ExperienceService {
    private ExperienceRepository experienceRepository;

    public ExperienceServiceImpl(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    @Override
    public List<Experience> getAllExperiences() {
        return experienceRepository.findAll();
    }
}
