package com.admin.adminapi.impl.controller;

import com.admin.adminapi.base.controller.GenericController;
import com.admin.adminapi.impl.dao.entities.Device;
import com.admin.adminapi.base.dao.entities.User;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import java.util.List;

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

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/devices")
    public @ResponseBody List<Device> getUserDevices(@PathVariable("id") int id) {
        return service.getUserDevices(id);
    }
}
