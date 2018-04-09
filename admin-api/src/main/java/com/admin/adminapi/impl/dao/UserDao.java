package com.admin.adminapi.impl.dao;

import com.admin.adminapi.base.dao.Dao;
import com.admin.adminapi.base.dao.entities.AbstractUser;
import com.admin.adminapi.impl.dao.entities.extended.ExtendedUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao extends Dao<AbstractUser> {

    @Override
    public AbstractUser find(Long id) {
        return em.createNamedQuery("User.getById", ExtendedUser.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<AbstractUser> findAll() {
        return em.createNamedQuery("User.getAll", AbstractUser.class)
                .getResultList();
    }
}
