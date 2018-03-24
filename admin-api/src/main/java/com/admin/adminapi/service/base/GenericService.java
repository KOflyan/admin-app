package com.admin.adminapi.service.base;

import com.admin.adminapi.dao.UserDao;
import com.admin.adminapi.dao.base.Dao;
import com.admin.adminapi.dto.base.Dto;
import com.admin.adminapi.entity.Account;
import com.admin.adminapi.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
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
