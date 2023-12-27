package com.coursework.project.service;

import com.coursework.project.entity.Role;
import com.coursework.project.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    User findByEmail(String email);

    User findById(Long id);

    List<User> findAllUsers();

    void deleteById(Long id);


    //Role findById(long theId);
}
