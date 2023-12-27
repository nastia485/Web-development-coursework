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
        System.out.println("STATUSES: "+statuses);
        System.out.println("REQUESTS: "+requests);
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
        List<Client> clients = requestService.findClientsByVolunteer(volunteer);
        model.addAttribute("clients", clients);
        return "volunteer/clients";
    }

    @PostMapping("/saveStatus")
    public String saveStatus(@RequestParam("requestId") Long theId,
                                    @Valid @ModelAttribute("currStatus") Status status) {
        Status statusForRequest = statusService.findById(status.getId());
        System.out.println("STATUS FROM UI: "+status);
        System.out.println("STATUS FROM DB: "+statusForRequest);

        Request updRequest = requestService.findById(theId);
        updRequest.setStatus(statusForRequest);
        System.out.println("REQUEST TO SET VALUE: "+updRequest);
        requestService.saveRequest(updRequest);

        return "redirect:/volunteer/requests";
    }
//
//    @GetMapping("/showFormForRequestUpdate")
//    public String showFormForUpdate(@RequestParam("requestId") Long theId, Model model) {
////        User user = userService.findById(theId);
////
////        model.addAttribute("user", user);
//        return "volunteer/request-edit";
//    }
//
//    @PostMapping("/saveRequest")
//    public String saveUser(@ModelAttribute("user") User user) {
//        // save employee
////        Role role = roleService.findByName(user.getRole().getName());
////        //Role role = user.getRole();
////        user.setRole(role);
////
////        userService.saveUser(user);
//        // redirect
//        return "redirect:/volunteer/requests";
//    }
}
