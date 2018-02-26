package com.admin.adminapi.controller;

import com.admin.adminapi.controller.base.GenericController;
import com.admin.adminapi.entity.User;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Controller
@EnableAutoConfiguration
@RequestMapping("/user")
public class UserController extends GenericController<User> {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/view").setViewName("main");
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getUsers(Model model) {
        model.addAttribute("users", service.getAll());
        return "main";
    }
}
