package com.coursework.project.service;

import com.coursework.project.entity.Status;

import java.util.List;

public interface StatusService {
    Status findById(Long id);

    List<Status> findAll();
    Status findByName(String statusName);
}
