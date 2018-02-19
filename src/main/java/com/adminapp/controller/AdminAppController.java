package com.adminapp.controller;

import com.adminapp.entity.User;
import com.adminapp.service.AdminAppService;
import lombok.Getter;
import lombok.Setter;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Controller
@EnableAutoConfiguration
public class AdminAppController extends WebMvcConfigurerAdapter{

    private final Logger logger = Logger.getLogger(AdminAppController.class);

    @Getter @Setter private AdminAppService service;

    @Autowired
    public AdminAppController(AdminAppService service) {
        this.service = service;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("main");
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public String getUsers(Model model) {
        model.addAttribute("users", service.getAllUsers());
        return "main";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/users")
    public @ResponseBody
    List<User> getAllUsers() {
        return service.getAllUsers();
    }
}
