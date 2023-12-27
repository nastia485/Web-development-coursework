package com.coursework.project.controller;

import com.coursework.project.entity.Client;
import com.coursework.project.entity.Request;
import com.coursework.project.service.ClientService;
import com.coursework.project.service.RequestService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {
    private RequestService requestService;

    private ClientService clientService;

    public ClientController(RequestService requestService,
                            ClientService clientService) {
        this.requestService = requestService;
        this.clientService = clientService;
    }

    @GetMapping("/requests")
    public String getRequests(Model model, Authentication authentication) {

        String email = authentication.getName();
        Client client = clientService.findClientByEmail(email);
        List<Request> requests = requestService.findByClient(client);
        model.addAttribute("requests", requests);
        return "client/requests";
    }
}
