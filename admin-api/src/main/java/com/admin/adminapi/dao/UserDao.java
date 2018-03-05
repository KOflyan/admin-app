package com.admin.adminapi.dao;

import com.admin.adminapi.dao.base.Dao;
import com.admin.adminapi.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao extends Dao<User> {
//
//    @Override
//    public List<User> getAll() {
//        return entityManager.createNamedQuery("User.getFullUserInfo", User.class)
//                .setParameter("account_id", 2)
//                .getResultList();
//    }
}
