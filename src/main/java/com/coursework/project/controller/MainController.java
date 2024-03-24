package com.coursework.project.controller;

import com.coursework.project.entity.Client;
import com.coursework.project.entity.Volunteer.Volunteer;
import com.coursework.project.service.ClientService;
import com.coursework.project.service.VolunteerService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.List;

@Controller
public class MainController {
    private VolunteerService volunteerService;
    private ClientService clientService;

    public MainController(VolunteerService volunteerService,
                          ClientService clientService) {
        this.volunteerService = volunteerService;
        this.clientService = clientService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }


    @GetMapping("/volunteers")
    public String getVolunteers(Model model, Authentication authentication) {
        List<Volunteer> theVolunteers = volunteerService.findAll();

        model.addAttribute("volunteers", theVolunteers);
        return "list-volunteers";
    }

    @GetMapping("/account")
    public String viewAccountPage(Authentication authentication) {
        System.out.println(authentication);
        if (authentication != null && authentication.isAuthenticated()) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            System.out.println(authorities);

            if (authorities.stream().anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"))) {
                return "redirect:/admin/users";
            } else if (authorities.stream().anyMatch(role -> role.getAuthority().equals("ROLE_VOLUNTEER"))) {
                return "redirect:/volunteer/requests";
            } else {
                return "redirect:/client/requests";
            }
        } else {
            return "redirect:/login";
        }
    }

}

