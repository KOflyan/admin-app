package com.admin.adminapi.impl.controller;

import com.admin.adminapi.base.controller.GenericController;
import com.admin.adminapi.base.dao.entities.AbstractAccount;
import com.admin.adminapi.impl.service.AccountService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Controller
@EnableAutoConfiguration
@RequestMapping("/account")
public class AccountController extends GenericController<AbstractAccount> {

    public AccountController(AccountService service) {
        super(service);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/countByType")
    public @ResponseBody Set<AbstractAccount> countAccountsByType() {
        return ((AccountService) service).countAccountsByType();
    }
}
