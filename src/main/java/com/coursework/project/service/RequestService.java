package com.coursework.project.service;

import com.coursework.project.entity.Admin;
import com.coursework.project.entity.Client;
import com.coursework.project.entity.Request;
import com.coursework.project.entity.Volunteer.Volunteer;

import java.util.List;

public interface RequestService {
    void saveRequest(Request request);

    List<Request> findByClient(Client client);

    List<Request> findByVolunteer(Volunteer volunteer);

    List<Client> findClientsByVolunteer(Volunteer volunteer);

    Request findById(Long id);

    void deleteById(Long theId);

}
