package com.admin.adminapi.controller;

import com.admin.adminapi.controller.base.GenericController;
import com.admin.adminapi.entity.Admin;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableAutoConfiguration
@RequestMapping("/admin")
public class AdminController extends GenericController<Admin> {
}
