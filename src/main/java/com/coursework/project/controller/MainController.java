package com.coursework.project.controller;

import com.coursework.project.entity.Volunteer.Volunteer;
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

    public MainController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }


    @GetMapping("/volunteers")
    public String getVolunteers(Model model) {
        List<Volunteer> theVolunteers = volunteerService.findAll();
        System.out.println("_______________");
        System.out.println(theVolunteers);
        System.out.println("_______________");

        // add to the spring model
        model.addAttribute("volunteers", theVolunteers);
        return "list-volunteers";
    }
    @GetMapping("/account")
    public String viewAccountPage(Authentication authentication) {
        System.out.println(authentication);
        if (authentication != null && authentication.isAuthenticated()) {
            // Отримання ролі користувача
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

//    @GetMapping("/volunteer/requests")
//    public String getVolunteerRequests(){
//        return "volunteer/requests";
//    }
//
//    @GetMapping("/client/requests")
//    public String getClientRequests(){
//        return "client/requests";
//    }
}

