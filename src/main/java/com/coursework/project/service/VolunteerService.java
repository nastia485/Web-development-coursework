package com.coursework.project.service;

import com.coursework.project.entity.Admin;
import com.coursework.project.entity.Volunteer.Volunteer;
import com.coursework.project.repository.VolunteerRepository;

import java.util.List;

public interface VolunteerService {
    void saveVolunteer(Volunteer volunteer);

    List<Volunteer> findAll();

    Volunteer findById(Long theId);

    Volunteer findByEmail(String email);

    Volunteer findSimilar(Volunteer volunteer);



}
