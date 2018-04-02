package com.admin.adminapi.impl.controller;

import com.admin.adminapi.base.controller.GenericController;
import com.admin.adminapi.base.dao.entities.AbstractAccount;
import com.admin.adminapi.impl.service.AccountService;
import com.admin.adminapi.utils.Messages;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.NoResultException;

@Controller
@EnableAutoConfiguration
@RequestMapping("/account")
public class AccountController extends GenericController<AbstractAccount> {

    @RequestMapping(method = RequestMethod.POST, path = "/setActiveState")
    public @ResponseBody ResponseEntity setActiveState(@RequestParam(name = "id") Long id,
                                                       @RequestParam(name = "isActive") boolean isActive) {
        try {
            ((AccountService) service).setActivateState(id, isActive);
        } catch (NoResultException ex) {
            logger.warn(Messages.ENTITY_NO_FOUND_ERROR);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        logger.info(Messages.SUCCESS);
        return new ResponseEntity(HttpStatus.OK);
    }
}
