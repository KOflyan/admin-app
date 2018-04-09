package com.admin.adminapi.base.controller;

import com.admin.adminapi.base.dao.entities.AbstractEntity;
import com.admin.adminapi.base.dto.Dto;
import com.admin.adminapi.base.service.GenericService;
import com.admin.adminapi.utils.Messages;
import lombok.Getter;
import lombok.Setter;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.persistence.NoResultException;
import javax.validation.Valid;
import java.util.List;

@Getter @Setter
public abstract class GenericController<T extends AbstractEntity> extends WebMvcConfigurerAdapter {

    protected final Logger logger = Logger.getLogger(GenericController.class);

    protected final GenericService<T> service;

    @Autowired
    public GenericController(GenericService<T> service) {
        this.service = service;
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

    @RequestMapping(method = RequestMethod.POST, path = "/save")
    public @ResponseBody ResponseEntity create(@RequestBody @Valid Dto<T> dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            logger.error(Messages.DTO_ERROR);
            System.out.println(dto);
            System.out.println(bindingResult.getAllErrors());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        service.save(dto);
        logger.info(Messages.SUCCESS);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/delete/{id}")
    public @ResponseBody ResponseEntity delete(@PathVariable("id") Long id) {
        try {
            service.delete(id);
        } catch (NoResultException ex) {
            logger.warn(Messages.ENTITY_NOT_FOUND_ERROR);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        logger.info(Messages.SUCCESS);

        return new ResponseEntity(HttpStatus.OK);
    }
}
