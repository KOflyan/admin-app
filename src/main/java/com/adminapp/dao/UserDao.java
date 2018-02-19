package com.adminapp.dao;

import com.adminapp.dao.base.Dao;
import com.adminapp.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDao extends Dao<User> {


    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAll() {
        return entityManager.createNativeQuery("SELECT * FROM User", User.class)
                .getResultList();
    }

    @Override
    public User getById() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(User user) {

    }
}
