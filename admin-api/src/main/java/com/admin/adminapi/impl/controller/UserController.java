package com.admin.adminapi.impl.controller;

import com.admin.adminapi.base.controller.GenericController;
import com.admin.adminapi.base.dao.entities.AbstractUser;
import com.admin.adminapi.impl.service.UserService;
import com.admin.adminapi.utils.Messages;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import javax.persistence.NoResultException;

@Controller
@EnableAutoConfiguration
@RequestMapping("/user")
public class UserController extends GenericController<AbstractUser> {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/view").setViewName("main");
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getUsers(Model model) {
        model.addAttribute("users", service.findAll());
        return "main";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/setActiveState")
    public @ResponseBody ResponseEntity setActiveState(@RequestParam(name = "id") Long id,
                                                       @RequestParam(name = "isActive") boolean isActive) {
        try {
            ((UserService) service).setActivateState(id, isActive);
        } catch (NoResultException ex) {
            logger.warn(Messages.ENTITY_NO_FOUND_ERROR);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        logger.info(Messages.SUCCESS);
        return new ResponseEntity(HttpStatus.OK);
    }
}
