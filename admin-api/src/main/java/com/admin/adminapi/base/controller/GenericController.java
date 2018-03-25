package com.admin.adminapi.base.controller;

import com.admin.adminapi.base.dto.Dto;
import com.admin.adminapi.base.service.GenericService;
import com.admin.adminapi.utils.Messages;
import com.admin.adminapi.utils.Utils;
import lombok.Getter;
import lombok.Setter;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;
import java.util.List;

@Component
@Getter @Setter
public abstract class GenericController<T> extends WebMvcConfigurerAdapter {

    private final Logger logger = Logger.getLogger(GenericController.class);

    @Autowired
    protected GenericService<T> service;
    protected String className;

    public GenericController() {
        className = Utils.getClassName(Utils.resolveClassOfT(getClass(), GenericController.class));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public @ResponseBody List<T> getAll(@RequestParam(value = "skip", required = false) Integer skip,
                                        @RequestParam(value = "limit", required = false) Integer limit) {

        return skip == null || limit == null ? service.getAll() : service.getAll(skip, limit);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public @ResponseBody T getById(@PathVariable("id") int id) {
        return service.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/update")
    public String update(@RequestBody @Valid Dto<T> dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            logger.error(Messages.DTO_ERROR);
            throw new IllegalArgumentException();
        }

        service.update(dto);
        logger.info(Messages.SUCCESS);

        return "redirect:/";
    }

    // FIXME this seems not right
    @RequestMapping(method = RequestMethod.POST, path = "/delete")
    public String delete(@RequestParam("id") int id, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            logger.error(Messages.DTO_ERROR);
            throw new IllegalArgumentException();

        }

        service.delete(id);
        logger.info(Messages.SUCCESS);

        return "redirect:/";
    }


    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public String create(@RequestBody @Valid Dto<T> dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            logger.error(Messages.DTO_ERROR);
            throw new IllegalArgumentException();
        }

        service.create(dto);
        logger.info(Messages.SUCCESS);
        return "redirect:/";
    }

}
