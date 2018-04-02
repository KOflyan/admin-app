package com.admin.adminapi.base.controller;

import com.admin.adminapi.base.dao.entities.AbstractEntity;
import com.admin.adminapi.base.dto.Dto;
import com.admin.adminapi.base.service.GenericService;
import com.admin.adminapi.utils.Messages;
import com.admin.adminapi.utils.Utils;
import lombok.Getter;
import lombok.Setter;
import org.jboss.logging.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;
import java.util.List;

@Getter @Setter
public abstract class GenericController<T extends AbstractEntity> extends WebMvcConfigurerAdapter {

    private final Logger logger = Logger.getLogger(GenericController.class);

    protected GenericService<T> service;
    protected String className;

    public GenericController(GenericService<T> service) {
        this.service = service;
        className = Utils.getClassName(Utils.resolveClassOfT(getClass(), GenericController.class));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public @ResponseBody List<T> getAll(@RequestParam(value = "skip", required = false) Integer skip,
                   @RequestParam(value = "limit", required = false) Integer limit) {

//        return skip == null || limit == null ? service.getAll() : service.getAll(skip, limit);
        return service.getAll();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public @ResponseBody T getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/update")
    public String update(@RequestBody @Valid Dto<T> dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            logger.error(Messages.DTO_ERROR);
            throw new IllegalArgumentException();
        }

        service.createOrUpdate(dto);
        logger.info(Messages.SUCCESS);

        return "redirect:/";
    }

    // FIXME this seems not right
    @RequestMapping(method = RequestMethod.POST, path = "/delete")
    public String delete(@RequestParam("id") Long id, BindingResult bindingResult) {

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
            logger.error(Messages.DTO_ERROR.getMessage());
            logger.error(dto);
            throw new IllegalArgumentException();
        }

        service.createOrUpdate(dto);
        logger.info(Messages.SUCCESS.getMessage());
        return "redirect:/";
    }

}
