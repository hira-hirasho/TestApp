package com.example.testapp.service;

import java.util.HashSet;

import com.example.testapp.model.Professor;
import com.example.testapp.repository.ProfessorMasterRepository;

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
public class ProfessorDetailsService implements UserDetailsService {
    @Autowired
    private final ProfessorMasterRepository repository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        var professor = repository.findByLoginId(loginId);
        if (professor == null) {
            throw new UsernameNotFoundException(loginId + " Not Found");
        }
        return createUserDetails(professor);
    }

    public User createUserDetails(Professor professor) {
        var grantedAuthorities = new HashSet<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_PROFESSOR"));

        return new User(professor.getLoginId(), professor.getPassword(), grantedAuthorities);
    }
}
