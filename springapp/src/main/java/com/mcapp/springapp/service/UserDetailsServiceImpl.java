package com.mcapp.springapp.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.mcapp.springapp.common.dto.UsuarioDto;
import com.mcapp.springapp.repository.UsuarioDao;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

public class UserDetailsServiceImpl implements UserDetailsService {
	
    @Resource
    private UsuarioDao daoUsuario;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsuarioDto user = this.daoUsuario.getUsuarioByEmail(email);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (String rol : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(rol));
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }
}