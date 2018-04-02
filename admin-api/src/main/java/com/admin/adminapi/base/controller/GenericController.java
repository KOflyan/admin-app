package com.admin.adminapi.base.controller;

import com.admin.adminapi.base.dao.entities.AbstractEntity;
import com.admin.adminapi.base.dto.Dto;
import com.admin.adminapi.base.service.GenericService;
import com.admin.adminapi.utils.Messages;
import com.admin.adminapi.utils.Utils;
import lombok.Getter;
import lombok.Setter;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Getter @Setter
public abstract class GenericController<T extends AbstractEntity> extends WebMvcConfigurerAdapter {

    private final Logger logger = Logger.getLogger(GenericController.class);
    protected GenericService<T> service;
    protected String className;

    @Autowired
    public GenericController(GenericService<T> service) {
        this.service = service;
        className = Utils.getClassName(Utils.resolveClassOfT(getClass(), GenericController.class));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public @ResponseBody List<T> findAll(@RequestParam(value = "skip", required = false) Integer skip,
                   @RequestParam(value = "limit", required = false) Integer limit) {

        return skip == null || limit == null ? service.findAll() : service.findAll(skip, limit);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public @ResponseBody T find(@PathVariable("id") Long id) {
        return service.find(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public @ResponseBody ResponseEntity create(@RequestBody @Valid Dto<T> dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            logger.error(Messages.DTO_ERROR);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        service.create(dto);
        logger.info(Messages.SUCCESS);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/update/{id}")
    public @ResponseBody ResponseEntity update(@PathVariable("id") Long id) {

        try {
            service.update(id);
        } catch (EntityNotFoundException ex) {
            logger.info(Messages.ENTITY_NO_FOUND_ERROR);
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        }

        logger.info(Messages.SUCCESS);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/delete/{id}")
    public @ResponseBody ResponseEntity delete(@PathVariable("id") Long id) {
        try {
            service.delete(id);
        } catch (EntityNotFoundException ex) {
            logger.warn(Messages.ENTITY_NO_FOUND_ERROR);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        logger.info(Messages.SUCCESS);

        return new ResponseEntity(HttpStatus.OK);
    }
}
