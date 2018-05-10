package com.admin.adminapi.base.controller;


import com.admin.adminapi.swagger.AuthApi;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;


@Controller
@EnableAutoConfiguration
public class AuthController implements AuthApi {


    @RequestMapping(method = RequestMethod.GET, path = "/getAuthority")
    public @ResponseBody
    Collection<? extends GrantedAuthority> getAuthorities() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            return auth.getAuthorities();
        }

        return null;
    }
}
