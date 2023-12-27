package com.coursework.project.service.impl;

import com.coursework.project.entity.Role;
import com.coursework.project.repository.AdminRepository;
import com.coursework.project.repository.RoleRepository;
import com.coursework.project.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
