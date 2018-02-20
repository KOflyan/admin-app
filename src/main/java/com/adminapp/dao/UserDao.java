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
    public User getById(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void add(User user) {

    }

    /**
     * This will fetch all info about user (including his accounts and devices).
     * @param id
     */
    public void getFull(int id) {

    }
}
