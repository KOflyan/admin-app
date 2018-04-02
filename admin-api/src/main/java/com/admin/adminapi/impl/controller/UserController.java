package com.admin.adminapi.impl.controller;

import com.admin.adminapi.base.controller.GenericController;
import com.admin.adminapi.base.dao.entities.AbstractUser;
import com.admin.adminapi.impl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Controller
@EnableAutoConfiguration
@RequestMapping("/user")
public class UserController extends GenericController<AbstractUser> {

    @Autowired
    public UserController(UserService service) {
        super(service);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/view").setViewName("main");
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getUsers(Model model) {
        model.addAttribute("users", service.findAll());
        return "main";
    }
}
