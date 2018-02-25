package com.admin.adminapi.controller;

import com.admin.adminapi.dto.AdminDto;
import com.admin.adminapi.entity.Admin;
import com.admin.adminapi.service.base.AdminApiService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;
import java.util.List;

@Controller
@RequestMapping("/admin")
@EnableAutoConfiguration
public class AdminController extends WebMvcConfigurerAdapter {

    @Getter private AdminApiService<Admin> service;

    @Autowired
    public AdminController(AdminApiService<Admin> service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public  @ResponseBody List<Admin> getAll() {
        return service.getAll();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/id")
    public @ResponseBody Admin get(@RequestParam(value = "id") int id) {
        return service.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public String create(@RequestBody @Valid AdminDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // TODO error message
            return null;
        }
        return "redirect:/";
    }


}
