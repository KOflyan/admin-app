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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.persistence.NoResultException;
import javax.validation.Valid;
import java.util.Set;

@Getter @Setter
public abstract class GenericController<T extends AbstractEntity> extends WebMvcConfigurerAdapter {

    protected final Logger logger = Logger.getLogger(GenericController.class);

    protected final GenericService<T> service;

    @Autowired
    public GenericController(GenericService<T> service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public @ResponseBody Set<T> findAll(@RequestParam(value = "skip", required = false) Integer skip,
                                        @RequestParam(value = "limit", required = false) Integer limit,
                                        @RequestParam(value = "searchText", required = false) String searchText) {

        if (searchText != null) {
            return service.search(searchText);
        }

        return skip == null || limit == null ? service.findAll() : service.findAll(skip, limit);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public @ResponseBody T find(@PathVariable("id") Long id) {
        return service.find(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(method = RequestMethod.POST, path = "/save")
    public @ResponseBody ResponseEntity create(@RequestBody @Valid Dto<T> dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            logger.error(Messages.DTO_ERROR);
            logger.error(bindingResult.getAllErrors());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        service.save(dto);
        logger.info(Messages.SUCCESS);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(method = RequestMethod.POST, path = "/delete/{id}")
    public @ResponseBody ResponseEntity delete(@PathVariable("id") Long id) {

        try {
            service.delete(id);
        } catch (NoResultException ex) {
            logger.error(Messages.ENTITY_NOT_FOUND_ERROR);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        logger.info(Messages.SUCCESS);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/count")
    public @ResponseBody Long count() {
        return service.count();
    }
}
