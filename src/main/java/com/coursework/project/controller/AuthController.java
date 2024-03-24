package com.coursework.project.controller;

import com.coursework.project.entity.Admin;
import com.coursework.project.entity.Client;
import com.coursework.project.entity.Role;
import com.coursework.project.entity.User;
import com.coursework.project.entity.Volunteer.*;
import com.coursework.project.repository.RoleRepository;
import com.coursework.project.service.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthController {

    private UserService userService;
    private AdminService adminService;
    private RoleService roleService;
    private VolunteerService volunteerService;
    private BranchService branchService;
    private ConcernService concernService;
    private QualificationService qualificationService;
    private ExperienceService experienceService;
    private ClientService clientService;

    public AuthController(UserService userService,
                          AdminService adminService,
                          RoleService roleService,
                          VolunteerService volunteerService,
                          BranchService branchService,
                          ConcernService concernService,
                          QualificationService qualificationService,
                          ExperienceService experienceService,
                          ClientService clientService) {
        this.userService = userService;
        this.adminService = adminService;
        this.roleService = roleService;
        this.volunteerService = volunteerService;
        this.branchService = branchService;
        this.concernService = concernService;
        this.qualificationService = qualificationService;
        this.experienceService = experienceService;
        this.clientService = clientService;
    }


    @GetMapping("index")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/register/roles")
    public String chooseRole() {
        System.out.println("kuku");
        return "registration-roles";
    }

    @GetMapping("/register/adminRegistrationPage")
    public String getAdminRegistration(Model model) {
        //Role role = roleService.findById(1L);
        //System.out.println("ROLE IS: "+role);
        User user = new User();
//        user.setRole(role);
        Admin admin = new Admin();
        admin.setUser(user);
        //model.addAttribute("user", user);
        model.addAttribute("admin", admin);
        return "registration/reg-admin";
    }

    @GetMapping("/register/volunteerRegistrationPage")
    public String getVolunteerRegistration(Model model) {
        User user = new User();
        Volunteer volunteer = new Volunteer();
        volunteer.setUser(user);
        List<Branch> branchOptions = branchService.getAllBranches();
        List<Concern> concernOptions = concernService.getAllConcerns();
        System.out.println(concernOptions);
        List<Qualification> qualificationOptions = qualificationService.getAllQualifications();
        List<Experience> experienceOptions = experienceService.getAllExperiences();

       // model.addAttribute("user", user);
        model.addAttribute("volunteer", volunteer);
        model.addAttribute("branchOptions", branchOptions);
        model.addAttribute("concernOptions", concernOptions);
        model.addAttribute("qualificationOptions", qualificationOptions);
        model.addAttribute("experienceOptions", experienceOptions);
        return "registration/reg-volunteer";
    }

    @GetMapping("/register/clientRegistrationPage")
    public String getClientRegistration(Model model) {
        User user = new User();
        Client client = new Client();
        client.setUser(user);

        model.addAttribute("client", client);
        return "registration/reg-client";
    }

    // handler method to handle user registration request
    @GetMapping("register")
    public String showRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }


    @PostMapping("/register/saveAdmin")
    public String adminRegistration(
                                    @Valid @ModelAttribute("admin") Admin admin,
                                    BindingResult result,
                                    Model model) {
        User user = admin.getUser();
        String check = checkUser(user, result, model);
        if (!check.equals("")) return check;
        Role role = roleService.findById(1L);
        user.setRole(role);
        userService.saveUser(user);
        admin.setUser(user);
        adminService.saveAdmin(admin);
        return "redirect:/login?success";
    }

    @PostMapping("/register/saveVolunteer")
    public String volunteerRegistration(
            @Valid @ModelAttribute("volunteer") Volunteer volunteer,
            BindingResult result,
            Model model) {
        User user = volunteer.getUser();
        String check = checkUser(user, result, model);
        if (!check.equals("")) return check;
        Role role = roleService.findById(2L);
        //user = volunteer.getUser();
        user.setRole(role);
        userService.saveUser(user);
        volunteer.setUser(user);
        volunteerService.saveVolunteer(volunteer);
        System.out.println("Volunteer user is: " + userService.findByEmail(user.getEmail()));

        return "redirect:/login?success";
    }

    @PostMapping("/register/saveClient")
    public String clientRegistration(
                                     @Valid @ModelAttribute("client") Client client,
                                     BindingResult result,
                                     Model model) {
        User user = client.getUser();
        String check = checkUser(user, result, model);
        if (!check.equals("")) return check;
        Role role = roleService.findById(3L);
        user.setRole(role);
        userService.saveUser(user);
        client.setUser(user);

        clientService.saveClient(client);
        return "redirect:/login?success";
    }

    @GetMapping("/users")
    public String listRegisteredUsers(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    private String checkUser(User user, BindingResult result, Model model) {
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        return "";
    }
}
