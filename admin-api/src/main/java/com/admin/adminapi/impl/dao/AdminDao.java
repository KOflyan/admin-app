package com.admin.adminapi.impl.dao;


import com.admin.adminapi.base.dao.Dao;
import com.admin.adminapi.impl.dao.entities.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminDao extends Dao<Admin> {

    public Admin findByUsername(String username) {

        return em.createQuery("SELECT a FROM Admin a WHERE a.username = :username", Admin.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public List<Admin> search(String searchText) {
        return null;
    }
}
