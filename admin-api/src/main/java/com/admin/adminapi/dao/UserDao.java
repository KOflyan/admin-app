package com.admin.adminapi.dao;

import com.admin.adminapi.dao.base.Dao;
import com.admin.adminapi.entity.base.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao extends Dao<User> {

    @Override
    public User getById(int id) {
        return em.createNamedQuery("User.fullInfo", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }


    @Override
    public List<User> getAll() {
        return em.createNamedQuery("User.getAll", User.class)
                .getResultList();
    }
}
