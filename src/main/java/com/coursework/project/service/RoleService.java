package com.coursework.project.service;

import com.coursework.project.entity.Admin;
import com.coursework.project.entity.Role;

public interface RoleService {
    Role findById(Long id);

    Role findByName(String name);
}
