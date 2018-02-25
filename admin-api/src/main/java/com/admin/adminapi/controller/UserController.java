package com.admin.adminapi.controller;

import com.admin.adminapi.dto.AccountDto;
import com.admin.adminapi.dto.DeviceDto;
import com.admin.adminapi.dto.UserDto;
import com.admin.adminapi.entity.Account;
import com.admin.adminapi.entity.Device;
import com.admin.adminapi.entity.User;
import com.admin.adminapi.service.base.AdminApiService;
import lombok.Getter;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;
import java.util.List;

@Controller
@EnableAutoConfiguration
@RequestMapping("/user")
public class UserController extends WebMvcConfigurerAdapter{

    private final Logger logger = Logger.getLogger(UserController.class);

    @Getter private AdminApiService<User> userService;
    @Getter private AdminApiService<Account> accountService;
    @Getter private AdminApiService<Device> deviceService;

    @Autowired
    public UserController(AdminApiService<User> userService,
                          AdminApiService<Account> accountService,
                          AdminApiService<Device> deviceService) {

        this.userService = userService;
        this.accountService = accountService;
        this.deviceService = deviceService;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/user").setViewName("main");
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "main";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/id")
    public @ResponseBody User getUser(@RequestParam("id") int id) {
        logger.info("ID called: " + id);
        return userService.getById(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public @ResponseBody List<User> getAllUsers() {
        return userService.getAll();
    }


    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public String createUser(@RequestBody @Valid UserDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // TODO error message here
            return null;
        }

        userService.create(dto);

        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create/account")
    public String createAccount(@RequestBody @Valid AccountDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // TODO error message here
            return null;
        }

        accountService.create(dto);

        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create/device")
    public String createDevice(@RequestBody @Valid DeviceDto dto, @PathParam("id") BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // TODO error message here
            return null;
        }

        deviceService.create(dto);

        return "redirect:/";
    }

}
