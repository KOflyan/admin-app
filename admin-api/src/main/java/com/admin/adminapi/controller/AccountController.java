package com.admin.adminapi.controller;

import com.admin.adminapi.controller.base.GenericController;
import com.admin.adminapi.entity.Account;
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
