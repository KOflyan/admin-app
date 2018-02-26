package com.admin.adminapi.controller.base;

import com.admin.adminapi.dto.base.Dto;
import com.admin.adminapi.service.base.GenericService;
import com.admin.adminapi.utils.Messages;
import com.admin.adminapi.utils.Utils;
import lombok.Getter;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

public abstract class GenericController<T> extends WebMvcConfigurerAdapter {

    private final Logger logger = Logger.getLogger(GenericController.class);

    @Autowired
    @Getter protected GenericService<T> service;
    @Getter protected String className;

    public GenericController() {
        className = Utils.getClassName(Utils.resolveClassOfT(getClass(), GenericController.class));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public @ResponseBody List<T> getAll() {
        return service.getAll();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/id")
    public @ResponseBody T getById(@PathParam("id") int id) {
        return service.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/update")
    public String update(@RequestBody @Valid Dto<T> dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            logger.error(Messages.DTO_ERROR);
        }

        service.update(dto);
        logger.info(Messages.SUCCESS);

        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/delete")
    public String delete(@RequestParam("id") int id, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            logger.error(Messages.DTO_ERROR);
        }

        service.delete(id);
        logger.info(Messages.SUCCESS);

        return "redirect:/";
    }


    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public String create(@RequestBody @Valid Dto<T> dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            logger.error(Messages.DTO_ERROR);
        }

        service.create(dto);
        logger.info(Messages.SUCCESS);
        return "redirect:/";
    }

}
