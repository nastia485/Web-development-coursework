package com.coursework.project.service.impl;

import com.coursework.project.entity.Client;
import com.coursework.project.repository.ClientRepository;
import com.coursework.project.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void saveClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public Client findClientByUserId(Long userId) {
        List<Client> clients = clientRepository.findAll();

        for (Client client : clients) {
            System.out.println("-------");
            System.out.println(client.getUser().getEmail());
            System.out.println("-------");

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


}
