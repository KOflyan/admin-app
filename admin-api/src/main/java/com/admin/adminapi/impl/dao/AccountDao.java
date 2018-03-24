package com.admin.adminapi.impl.dao;

import com.admin.adminapi.base.dao.Dao;
import com.admin.adminapi.base.dao.entities.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDao extends Dao<Account> {

//    @Override
//    public Account getById(int id) {
//        return em.createNamedQuery("Account.fullInfo", Account.class)
//                .setParameter("id", id)
//                .getSingleResult();
//    }


    @Override
    public List<Account> getAll() {
        return em.createNamedQuery("Account.fullInfo", Account.class)
                .getResultList();
    }
}
