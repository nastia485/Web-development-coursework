package com.coursework.project.service;

import com.coursework.project.entity.Client;
import com.coursework.project.entity.Volunteer.Volunteer;

public interface ClientService {
    void saveClient(Client client);

    Client findClientByUserId(Long userId);

    Client findClientByEmail(String email);

    //boolean canSubmitRequest(Client client);

}
