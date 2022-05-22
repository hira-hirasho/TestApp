package com.example.testapp.service;

import java.util.HashSet;

import com.example.testapp.model.Student;
import com.example.testapp.repository.StudentMasterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StudentDetailsService implements UserDetailsService {
    @Autowired
    private final StudentMasterRepository repository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        var student = repository.findByLoginId(loginId);
        if (student == null) {
            throw new UsernameNotFoundException(loginId + " Not Found");
        }
        return createUserDetails(student);
    }

    public User createUserDetails(Student student) {
        var grantedAuthorities = new HashSet<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));

        return new User(student.getLoginId(), student.getPassword(), grantedAuthorities);
    }
}
