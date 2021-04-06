package com.employee.ebf.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.employee.ebf.model.AdminEntity;
import com.employee.ebf.repo.AdminRepository;

@Service
public class UserDetailsServices implements UserDetailsService {
	@Autowired
    private AdminRepository adminRepository;

   
	@Override
    public UserDetails loadUserByUsername(String uname) throws UsernameNotFoundException {
        AdminEntity adminEntity = adminRepository.findByUsername(uname);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        adminEntity.setPassword(passwordEncoder.encode(adminEntity.getPassword()));
        if(adminEntity == null) {
            throw new UsernameNotFoundException(uname);
        } else {
            return adminEntity;
        }
    }
}
