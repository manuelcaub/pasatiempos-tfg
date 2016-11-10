package com.mcapp.springapp.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.mcapp.springapp.domain.User;
import com.mcapp.springapp.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

public class UserDetailsServiceImpl implements UserDetailsService {
	
    @Resource
    private UserRepository repUser;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.repUser.getUserByEmail(email);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (String rol : user.getRoles().stream().map(x -> x.getRole()).collect(Collectors.toList())){
            grantedAuthorities.add(new SimpleGrantedAuthority(rol));
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }
}