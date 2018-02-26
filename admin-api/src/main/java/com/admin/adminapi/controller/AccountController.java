package com.admin.adminapi.controller;

import com.admin.adminapi.controller.base.GenericController;
import com.admin.adminapi.entity.Account;
import com.admin.adminapi.service.base.GenericService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableAutoConfiguration
@RequestMapping("/account")
public class AccountController extends GenericController<Account> {

}
