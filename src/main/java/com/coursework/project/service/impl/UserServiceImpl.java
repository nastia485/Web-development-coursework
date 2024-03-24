package com.coursework.project.service.impl;

import com.coursework.project.entity.Admin;
import com.coursework.project.entity.Client;
import com.coursework.project.entity.Role;
import com.coursework.project.entity.User;
import com.coursework.project.entity.Volunteer.Volunteer;
import com.coursework.project.repository.*;
import com.coursework.project.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    private AdminRepository adminRepository;
    private VolunteerRepository volunteerRepository;
    private ClientRepository clientRepository;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           AdminRepository adminRepository,
                           VolunteerRepository volunteerRepository,
                           ClientRepository clientRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.adminRepository = adminRepository;
        this.volunteerRepository = volunteerRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
