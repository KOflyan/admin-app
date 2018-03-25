package com.admin.adminapi.impl.controller;

import com.admin.adminapi.base.controller.GenericController;
import com.admin.adminapi.base.dao.entities.AbstractAccount;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration
@RequestMapping("/account")
public class AccountController extends GenericController<AbstractAccount> {
}
