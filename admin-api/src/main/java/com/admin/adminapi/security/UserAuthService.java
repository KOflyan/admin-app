package com.admin.adminapi.security;

import com.admin.adminapi.base.dao.Dao;
import com.admin.adminapi.impl.dao.AdminDao;
import com.admin.adminapi.impl.dao.entities.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserAuthService implements UserDetailsService {

    private Dao<Admin> dao;

    @Autowired
    public UserAuthService(Dao<Admin> dao) {
        this.dao = dao;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Admin admin = ((AdminDao) dao).findByUsername(username);

        if (admin == null) {
            throw new UsernameNotFoundException("Incorrect username: " + username);
        }

        GrantedAuthority authority = new SimpleGrantedAuthority(admin.getRole());


        return new User(admin.getUsername(), admin.getPassword(), Collections.singletonList(authority));
    }

}
