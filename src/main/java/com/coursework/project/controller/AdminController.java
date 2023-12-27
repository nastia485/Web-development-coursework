package com.coursework.project.controller;

import com.coursework.project.entity.Role;
import com.coursework.project.entity.User;
import com.coursework.project.service.RoleService;
import com.coursework.project.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    private RoleService roleService;

    public AdminController(UserService userService,
                           RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "admin/users-list";
    }


    @GetMapping("/showFormForUserUpdate")
    public String showFormForUpdate(@RequestParam("userId") Long theId, Model model) {
        User user = userService.findById(theId);

        model.addAttribute("user", user);
        return "admin/user-upd-form";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        // save employee
        Role role = roleService.findByName(user.getRole().getName());
        //Role role = user.getRole();
        user.setRole(role);

        userService.saveUser(user);
        // redirect
        return "redirect:/admin/users";
    }

    @GetMapping("/deleteUser")
    public String delete(@RequestParam("userId") Long theId) {
        userService.deleteById(theId);
        return "redirect:/admin/users";
    }

}
