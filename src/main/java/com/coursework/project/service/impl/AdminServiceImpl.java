package com.coursework.project.service.impl;


import com.coursework.project.entity.Admin;
import com.coursework.project.repository.AdminRepository;
import com.coursework.project.repository.RoleRepository;
import com.coursework.project.repository.UserRepository;
import com.coursework.project.service.AdminService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    private AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public void saveAdmin(Admin admin) {
        adminRepository.save(admin);
    }

}
