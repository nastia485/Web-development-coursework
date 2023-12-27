package com.coursework.project.service.impl;

import com.coursework.project.entity.Status;
import com.coursework.project.repository.StatusRepository;
import com.coursework.project.service.StatusService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {
    private StatusRepository statusRepository;

    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public Status findById(Long id) {
        return statusRepository.findById(id).get();
    }

    @Override
    public List<Status> findAll() {
        return statusRepository.findAll();
    }

    @Override
    public Status findByName(String statusName) {
        List<Status> statuses = statusRepository.findAll();
        for (Status status : statuses) {
            if (status.getStatusName().equals(statusName)) {
                return status;
            }
        }
        return null;
    }

}
