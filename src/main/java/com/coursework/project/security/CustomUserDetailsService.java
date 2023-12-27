package com.coursework.project.security;

//import com.coursework.project.entity.Role;
//import com.coursework.project.entity.User;
//import com.coursework.project.repository.UserRepository;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Collection;
//import java.util.stream.Collectors;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private UserRepository userRepository;
//
//    public CustomUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(email);
//
//        System.out.println(user);
//        if (user != null) {
//            return new org.springframework.security.core.userdetails.User(user.getEmail(),
//                    user.getPassword(),
//                    (Collection<? extends GrantedAuthority>) mapRolesToAuthorities(user.getRole()));
//
//        }else{
//            throw new UsernameNotFoundException("Invalid username or password.");
//        }
//    }
//
//    private GrantedAuthority mapRolesToAuthorities(Role role) {
//        return new SimpleGrantedAuthority(role.getName());
//    }
//}

import com.coursework.project.entity.Role;
import com.coursework.project.entity.User;
import com.coursework.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + email);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                getAuthorities(user.getRole())
        );
    }

    private Set<GrantedAuthority> getAuthorities(Role role) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(role.getName()));
        return authorities;
    }
}


