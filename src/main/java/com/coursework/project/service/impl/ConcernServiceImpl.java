package com.coursework.project.service.impl;

import com.coursework.project.entity.Volunteer.Branch;
import com.coursework.project.entity.Volunteer.Concern;
import com.coursework.project.repository.ConcernRepository;
import com.coursework.project.service.ConcernService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConcernServiceImpl implements ConcernService {
    private ConcernRepository concernRepository;

    public ConcernServiceImpl(ConcernRepository concernRepository) {
        this.concernRepository = concernRepository;
    }

    @Override
    public List<Concern> getAllConcerns() {
        return concernRepository.findAll();
    }
}
