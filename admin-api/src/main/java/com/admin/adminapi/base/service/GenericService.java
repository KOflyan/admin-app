package com.admin.adminapi.base.service;

import com.admin.adminapi.base.dao.Dao;
import com.admin.adminapi.base.dto.Dto;
import com.admin.adminapi.impl.dao.entities.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class GenericService<T> {

    @Autowired
    protected Dao<T> dao;

    public T getById(int id) {
        return dao.getById(id);
    }

    public List<T> getAll(int skip, int limit) {
        return dao.getAll(skip, limit);
    }

    public List<T> getAll() {
        return dao.getAll();
    }

    public void delete(int id) {
        dao.delete(id);
    }

    public void create(Dto<T> dto) {
        dao.create(dto.get());
    }

    public void update(Dto<T> dto) {
        dao.update(dto.get());
    }

    public List<Device> getUserDevices(int userId) {
        return dao.getUserDevices(userId);
    }
}
