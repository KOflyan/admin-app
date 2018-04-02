package com.admin.adminapi.impl.dao;

import com.admin.adminapi.base.dao.Dao;
import com.admin.adminapi.base.dao.entities.AbstractUser;

import java.util.List;

public class UserDao extends Dao<AbstractUser> {

    @Override
    public AbstractUser getById(int id) {
        return em.createNamedQuery("User.getById", AbstractUser.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<AbstractUser> getAll() {
        return em.createNamedQuery("User.getAll", AbstractUser.class)
                .getResultList();
    }
}
