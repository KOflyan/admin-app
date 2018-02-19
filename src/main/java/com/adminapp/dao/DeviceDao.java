package com.adminapp.dao;

import com.adminapp.dao.base.Dao;
import com.adminapp.entity.Device;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeviceDao extends Dao<Device> {

    @Override
    public List<Device> getAll() {
        return null;
    }

    @Override
    public Device getById() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Device device) {

    }
}
