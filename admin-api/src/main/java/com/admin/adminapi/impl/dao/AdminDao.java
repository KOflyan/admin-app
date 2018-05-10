package com.admin.adminapi.impl.dao;


import com.admin.adminapi.base.dao.Dao;
import com.admin.adminapi.impl.dao.entities.Admin;
import org.springframework.stereotype.Repository;
import javax.persistence.NoResultException;

import java.util.List;

@Repository
public class AdminDao extends Dao<Admin> {

    public Admin findByUsername(String username) {

        Admin admin = null;

        try {
            admin = em.createNamedQuery("Admin.findByUsername", Admin.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException ignored) {}

        return admin;
    }

    @Override
    public List<Admin> search(String searchText) {
        return null;
    }
}
