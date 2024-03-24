package com.coursework.project.controller;

import com.coursework.project.entity.*;
import com.coursework.project.entity.Volunteer.Volunteer;
import com.coursework.project.service.RequestService;
import com.coursework.project.service.StatusService;
import com.coursework.project.service.VolunteerService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/volunteer")
public class VolunteerController {

    private RequestService requestService;
    private VolunteerService volunteerService;

    private StatusService statusService;

    public VolunteerController(RequestService requestService,
                               VolunteerService volunteerService,
                               StatusService statusService) {
        this.requestService = requestService;
        this.volunteerService = volunteerService;
        this.statusService = statusService;
    }

    @GetMapping("/requests")
    public String getRequests(Authentication authentication, Model model) {
        String volunteerEmail = authentication.getName();
        Volunteer volunteer = volunteerService.findByEmail(volunteerEmail);
        List<Request> requests = requestService.findByVolunteer(volunteer);
        List<Status> statuses = statusService.findAll();
        statuses.remove(statuses.size() - 1);
        System.out.println("STATUSES: " + statuses);
        System.out.println("REQUESTS: " + requests);
        Status status = new Status();

        model.addAttribute("currStatus", status);
        model.addAttribute("requests", requests);
        model.addAttribute("statuses", statuses);
        return "volunteer/requests";
    }

    @GetMapping("/clients")
    public String getClients(Authentication authentication, Model model) {
        String volunteerEmail = authentication.getName();
        Volunteer volunteer = volunteerService.findByEmail(volunteerEmail);
        List<Client> clients = requestService.findActiveClientsByVolunteer(volunteer);
        model.addAttribute("clients", clients);
        return "volunteer/clients";
    }

    @PostMapping("/saveStatus")
    public String saveStatus(@RequestParam("requestId") Long theId,
                             @Valid @ModelAttribute("currStatus") Status status) {
        Status newStatus = statusService.findById(status.getId());
        Request request = requestService.findById(theId);
        request.setStatus(newStatus);
        requestService.saveRequest(request);
        if (newStatus.equals(statusService.findById(2L))) {
            Request newRequest = new Request();
            newRequest.setVolunteer(volunteerService.findSimilar(request.getVolunteer()));
            newRequest.setClient(request.getClient());
            newRequest.setStatus(statusService.findById(1L));
            newRequest.setIsRequestFromClient(0);
            requestService.saveRequest(newRequest);
        }

        return "redirect:/volunteer/requests";
    }

}
