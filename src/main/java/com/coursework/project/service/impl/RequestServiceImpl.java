package com.coursework.project.service.impl;

import com.coursework.project.entity.Client;
import com.coursework.project.entity.Request;
import com.coursework.project.entity.Status;
import com.coursework.project.entity.Volunteer.Volunteer;
import com.coursework.project.repository.RequestRepository;
import com.coursework.project.repository.StatusRepository;
import com.coursework.project.service.RequestService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {
    private RequestRepository requestRepository;
    private StatusRepository statusRepository;

    public RequestServiceImpl(RequestRepository requestRepository,
                              StatusRepository statusRepository) {
        this.requestRepository = requestRepository;
        this.statusRepository = statusRepository;
    }

    @Override
    public void saveRequest(Request request) {
        requestRepository.save(request);
    }

    @Override
    public List<Request> findByClient(Client client) {
        List<Request> clientRequests = new ArrayList<>();
        List<Request> requests = requestRepository.findAll();
        for (Request request : requests) {
            if (request.getClient().equals(client)) {
                clientRequests.add(request);
            }
        }
        return clientRequests;
    }

    @Override
    public List<Request> findByVolunteer(Volunteer volunteer) {
        List<Request> volunteerRequests = new ArrayList<>();
        List<Request> requests = requestRepository.findAll();
        for (Request request : requests) {
            if (request.getVolunteer().equals(volunteer)) {
                volunteerRequests.add(request);
            }
        }
        return volunteerRequests;
    }

    @Override
    public List<Client> findClientsByVolunteer(Volunteer volunteer) {
        List<Client> clients = new ArrayList<>();
        List<Request> requests = findByVolunteer(volunteer);
        Status status = statusRepository.findById(3L).get();
        for (Request request : requests) {
            if (request.getStatus().equals(status)) {
                clients.add(request.getClient());
            }
        }
        return clients;
    }

    @Override
    public Request findById(Long id) {
        return requestRepository.findById(id).get();
    }

    @Override
    public void deleteById(Long theId) {
        requestRepository.deleteById(theId);
    }
}
