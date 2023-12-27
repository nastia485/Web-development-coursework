package com.coursework.project.service.impl;

import com.coursework.project.entity.Admin;
import com.coursework.project.entity.Volunteer.Volunteer;
import com.coursework.project.repository.UserRepository;
import com.coursework.project.repository.VolunteerRepository;
import com.coursework.project.service.VolunteerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VolunteerServiceImpl implements VolunteerService {

    private VolunteerRepository volunteerRepository;
    private UserRepository userRepository;

    public VolunteerServiceImpl(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    @Override
    public void saveVolunteer(Volunteer volunteer) {
        volunteerRepository.save(volunteer);
    }

    @Override
    public List<Volunteer> findAll() {
        return volunteerRepository.findAll();
    }

    @Override
    public Volunteer findById(Long theId) {
        Optional<Volunteer> result = volunteerRepository.findById(theId);

        Volunteer theVolunteer = null;

        if (result.isPresent()) {
            theVolunteer = result.get();
        } else {
            // we didn't find the employee
            throw new RuntimeException("Did not find employee id - " + theId);
        }

        return theVolunteer;
    }

    @Override
    public Volunteer findByEmail(String email) {
        List<Volunteer> volunteers = volunteerRepository.findAll();
        Volunteer theVolunteer = new Volunteer();
        for (Volunteer volunteer : volunteers) {
            if (volunteer.getUser().getEmail().equals(email)) {
                theVolunteer = volunteer;
            }
        }
        return theVolunteer;
    }


}
