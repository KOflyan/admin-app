package com.admin.adminapi.controller;

import com.admin.adminapi.entity.User;
import com.admin.adminapi.service.AdminApiService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
@EnableAutoConfiguration
public class AdminApiController extends WebMvcConfigurerAdapter{

    private final Logger logger = Logger.getLogger(AdminApiController.class);

    @Autowired
    private AdminApiService service;


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("main");
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public String getUsers(Model model) {
        model.addAttribute("users", service.getAllUsers());
        return "main";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/user")
    public @ResponseBody User getUser(@PathParam("id") String id) {
        logger.info("ID called: " + id);
        return service.getUserById(Integer.valueOf(id));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/users")
    public @ResponseBody List<User> getAllUsers() {
        return service.getAllUsers();
    }
}
