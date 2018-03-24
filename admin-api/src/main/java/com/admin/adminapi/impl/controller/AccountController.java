package com.admin.adminapi.impl.controller;

import com.admin.adminapi.base.controller.GenericController;
import com.admin.adminapi.base.dao.entities.Account;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration
@RequestMapping("/account")
public class AccountController extends GenericController<Account> {

//    @RequestMapping(method = RequestMethod.GET, path = "/{id}/users")
//    public @ResponseBody List<User> getAccountUsers(@PathVariable("id") int id,
//                                                    @RequestParam("skip") int skip,
//                                                    @RequestParam("limit") int limit) {
//        return
//    }
}
