package com.coursework.project.controller;

import com.coursework.project.entity.Client;
import com.coursework.project.entity.Request;
import com.coursework.project.service.ClientService;
import com.coursework.project.service.RequestService;
import com.coursework.project.service.StatusService;
import com.coursework.project.service.VolunteerService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {
    private RequestService requestService;

    private ClientService clientService;

    private StatusService statusService;

    private VolunteerService volunteerService;

    public ClientController(RequestService requestService,
                            ClientService clientService,
                            StatusService statusService,
                            VolunteerService volunteerService) {
        this.requestService = requestService;
        this.clientService = clientService;
        this.statusService = statusService;
        this.volunteerService = volunteerService;
    }

    @GetMapping("/requests")
    public String getRequests(Model model, Authentication authentication) {

        String email = authentication.getName();
        Client client = clientService.findClientByEmail(email);
        //List<Request> requests = requestService.findSubmittedByClient(client);
        List<Request> requests = requestService.findByClient(client);
        int hasPsychologist = checkStatus(requests);

        model.addAttribute("requests", requests);
        model.addAttribute("hasPsychologist", hasPsychologist);

        System.out.println(requests);
        return "client/requests";
    }

    @PostMapping("/startCooperation")
    public String startCooperation(@RequestParam("requestId") Long requestId) {
        Request request = requestService.findById(requestId);
        request.setStatus(statusService.findById(4L));
        Integer isRequestFromClient = 1;
        request.setIsRequestFromClient(isRequestFromClient);
        requestService.saveRequest(request);
        return "redirect:/client/requests";
    }

    @PostMapping("/rejectCooperation")
    public String rejectCooperation(@RequestParam("requestId") Long requestId) {
        Request request = requestService.findById(requestId);
        if (request.getIsRequestFromClient() == 0) {
            Request newRequest = new Request();
            newRequest.setVolunteer(volunteerService.findSimilar(request.getVolunteer()));
            newRequest.setClient(request.getClient());
            newRequest.setStatus(statusService.findById(1L));
            newRequest.setIsRequestFromClient(0);
            requestService.saveRequest(newRequest);
        }
        requestService.deleteById(requestId);
        return "redirect:/client/requests";
    }

    public int checkStatus(List<Request> requests) {
        boolean hasStatus4 = requests.stream()
                .anyMatch(request -> request.getStatus().getId() == 4L);

        return hasStatus4 ? 1 : 0;
    }
}
