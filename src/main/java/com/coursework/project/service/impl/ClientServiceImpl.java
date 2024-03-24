package com.coursework.project.service.impl;

import com.coursework.project.entity.Client;
import com.coursework.project.entity.Request;
import com.coursework.project.repository.ClientRepository;
import com.coursework.project.service.ClientService;
import com.coursework.project.service.RequestService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;

    private RequestService requestService;

    public ClientServiceImpl(ClientRepository clientRepository,
                             RequestService requestService) {
        this.clientRepository = clientRepository;
        this.requestService = requestService;
    }

    @Override
    public void saveClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public Client findClientByUserId(Long userId) {
        List<Client> clients = clientRepository.findAll();
        for (Client client : clients) {
            if (client.getUser().getId().longValue() == userId.longValue()) {
                return client;
            }
        }
        return null;
    }

    @Override
    public Client findClientByEmail(String email) {
        List<Client> clients = clientRepository.findAll();
        for (Client client : clients) {
            if (client.getUser().getEmail().equals(email)) {
                return client;
            }
        }
        return null;
    }

//    @Override
//    public boolean canSubmitRequest(Client client) {
//        List<Request> clientsRequests = requestService.findByClient(client);
//        for (Request request : clientsRequests) {
//            if (request.getStatus().getId().equals(1L) || request.getStatus().getId().equals(3L)) {
//                return false;
//            }
//        }
//        return true;
//    }


}
