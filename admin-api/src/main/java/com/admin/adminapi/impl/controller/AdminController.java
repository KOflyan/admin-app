package com.admin.adminapi.impl.controller;

import com.admin.adminapi.base.controller.GenericController;
import com.admin.adminapi.impl.dao.entities.Admin;
import com.admin.adminapi.impl.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableAutoConfiguration
@RequestMapping("/admin")
public class AdminController extends GenericController<Admin> {

    @Autowired
    public AdminController(AdminService service) {
        super(service);
    }
}
