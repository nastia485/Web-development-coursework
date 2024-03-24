package com.coursework.project.controller;

import com.coursework.project.entity.Client;
import com.coursework.project.entity.Request;
import com.coursework.project.entity.Status;
import com.coursework.project.entity.User;
import com.coursework.project.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @GetMapping("/adminRegistrationPage")
    public String getAdminRegistration() {
        return "register";
    }

    @GetMapping("/volunteerRegistrationPage")
    public String getVolunteerRegistration() {
        return "register";
    }

    @GetMapping("/clientRegistrationPage")
    public String getClientRegistration() {
        return "register";
    }


    private RequestService requestService;
    private ClientService clientService;

    private VolunteerService volunteerService;

    private UserService userService;

    private StatusService statusService;


    public UserController(RequestService requestService,
                          ClientService clientService,
                          UserService userService,
                          VolunteerService volunteerService,
                          StatusService statusService) {
        this.requestService = requestService;
        this.clientService = clientService;
        this.userService = userService;
        this.volunteerService = volunteerService;
        this.statusService = statusService;
    }

    @PostMapping("/submitRequest/{volunteerId}")
    public String saveRequest(@PathVariable Long volunteerId, Authentication authentication) {
        String userName = authentication.getName();
        Request request = new Request();
        User user = userService.findByEmail(userName);
        Client client = clientService.findClientByUserId(user.getId());
        request.setClient(client);
        request.setIsRequestFromClient(1);
        request.setVolunteer(volunteerService.findById(volunteerId));
        Status status = statusService.findById(1L);
        request.setStatus(status);
        requestService.saveRequest(request);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        return "redirect:/volunteers";

    }

}
