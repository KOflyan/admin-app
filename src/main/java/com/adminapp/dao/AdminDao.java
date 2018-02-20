package com.adminapp.dao;

import com.adminapp.dao.base.Dao;
import com.adminapp.entity.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminDao extends Dao<Admin> {

    @Override
    public List<Admin> getAll() {
        return null;
    }

    @Override
    public Admin getById(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Admin admin) {

    }

    @Override
    public void add(Admin admin) {

    }
}
