package com.admin.adminapi.base.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

@Controller
@EnableAutoConfiguration
public class AuthController extends WebMvcConfigurerAdapter {


    @RequestMapping(method = RequestMethod.GET, path="/logout")
    public ResponseEntity logout(HttpServletRequest request, HttpServletResponse response) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @RequestMapping(method = RequestMethod.GET, path = "/getAuthority")
    public @ResponseBody Collection<? extends GrantedAuthority> getAuthorities(HttpServletRequest req) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();


        if (auth != null) {
            return auth.getAuthorities();
        }

        return null;
    }
}
