package com.coursework.project.service.impl;

import com.coursework.project.entity.Volunteer.Qualification;
import com.coursework.project.repository.QualificationRepository;
import com.coursework.project.service.QualificationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QualificationServiceImpl implements QualificationService {
    private QualificationRepository qualificationRepository;

    public QualificationServiceImpl(QualificationRepository qualificationRepository) {
        this.qualificationRepository = qualificationRepository;
    }

    @Override
    public List<Qualification> getAllQualifications() {
        return qualificationRepository.findAll();
    }
}
