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

    //change according to logic!!
    @Override
    public void saveUser(User user) {
        //encrypt the password once we integrate spring security
        //user.setPassword(userDto.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        Role role = roleRepository.findByName("ROLE_ADMIN");
//        if(role == null){
//            role = checkRoleExist();
//        }
//        user.setRole(role);
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
//        Role role = findById(id).getRole();
//        Long userId = null;
//        if (role.getId().equals(1L)) {
//            List<Admin> admins = adminRepository.findAll();
//            for (Admin admin : admins) {
//                if (Objects.equals(admin.getUser().getId(), id)) {
//                    userId = admin.getId();
//                    adminRepository.deleteById(userId);
//                    break;
//                }
//            }
//        }
//        if (role.getId().equals(2L)) {
//            List<Volunteer> volunteers = volunteerRepository.findAll();
//            for (Volunteer volunteer : volunteers) {
//                if (Objects.equals(volunteer.getUser().getId(), id)) {
//                    userId = volunteer.getId();
//                    volunteerRepository.deleteById(userId);
//                    break;
//                }
//            }
//        }
//        if (role.getId().equals(3L)) {
//            List<Client> clients = clientRepository.findAll();
//            for (Client client : clients) {
//                if (Objects.equals(client.getUser().getId(), id)) {
//                    userId = client.getId();
//                    clientRepository.deleteById(userId);
//                    break;
//                }
//            }
//        }
//        userRepository.deleteById(id);

    }


    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

//    public Role findById(long theId){
//        return roleRepository.findById(theId).get();
//    }
}
